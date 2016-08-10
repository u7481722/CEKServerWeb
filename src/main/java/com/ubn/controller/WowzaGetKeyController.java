package com.ubn.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.codec.binary.Hex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ubn.dto.ReturnDTO;
import com.ubn.dto.SetKeyDTO;
import com.ubn.dto.SetTokenDTO;
import com.ubn.repository.KeyRepository;

@RestController
public class WowzaGetKeyController {
	protected Logger logger = LoggerFactory.getLogger(WowzaGetKeyController.class);
	@Resource
	private KeyRepository keyRepository;
	Map<String, String> keyStore = new HashMap<String, String>();
	Map<String, String> tokenStore = new HashMap<String, String>();

	@RequestMapping(value={"/Key/{m3u8Name}"}, method={RequestMethod.GET})
	public void Keys(@PathVariable("m3u8Name") String strM3U8Name, @RequestParam("Token") String strToken,
			@RequestParam("UserId") String strUserId, HttpServletResponse response) {
		try {
			if (!keyRepository.findOne(strUserId).equals(strToken)) {
				response.setStatus(403);
			}
			logger.debug(keyStore.toString());
			logger.debug("strM3U8Name={}", strM3U8Name + ".mp4");

			String strKey = keyRepository.findOne(strM3U8Name + ".mp4");

			logger.debug("strKey={}", strKey);
			response.getOutputStream().write(Hex.decodeHex(strKey.toCharArray()));
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
			response.setStatus(403);
		}
	}

	@RequestMapping(value={"/SetKey"}, method={RequestMethod.POST})
	public ReturnDTO setKey(@RequestBody SetKeyDTO requestDTO) {
		logger.info("setKey requestDTO={}", requestDTO.toString());
		String strM3U8Name = requestDTO.getM3u8Name();
		String strKey = requestDTO.getKey();

		keyRepository.save(strM3U8Name, strKey);

		ReturnDTO returnDTO = new ReturnDTO();
		returnDTO.setReturnCode(0);
		returnDTO.setReturnMesssage("success");
		return returnDTO;
	}

	@RequestMapping(value = {"/SetToken"}, method={RequestMethod.POST})
	public ReturnDTO setToken(@RequestBody SetTokenDTO requestDTO) {
		logger.debug("setToken requestDTO={}", requestDTO.toString());
		String strToken = requestDTO.getToken();
		String strUserId = requestDTO.getUserId();

		keyRepository.save(strUserId, strToken);

		ReturnDTO returnDTO = new ReturnDTO();
		returnDTO.setReturnCode(0);
		returnDTO.setReturnMesssage("success");
		return returnDTO;
	}

	@RequestMapping(value={"/SetKeys"}, method={RequestMethod.POST})
	public ReturnDTO setKey(@RequestBody List<SetKeyDTO> requestDTOs) {
		logger.debug("SetKeys requestDTOs.size={}", Integer.valueOf(requestDTOs.size()));
		for (SetKeyDTO requestDTO : requestDTOs) {
			String strM3U8Name = requestDTO.getM3u8Name();
			String strKey = requestDTO.getKey();

			keyRepository.save(strM3U8Name, strKey);
		}
		ReturnDTO returnDTO = new ReturnDTO();
		returnDTO.setReturnCode(0);
		returnDTO.setReturnMesssage("success");
		return returnDTO;
	}

}
