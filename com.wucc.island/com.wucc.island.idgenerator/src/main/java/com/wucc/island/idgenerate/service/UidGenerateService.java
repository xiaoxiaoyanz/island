package com.wucc.island.idgenerate.service;

import com.baidu.fsg.uid.UidGenerator;

import com.wucc.island.api.idgenerator.UidGenerateServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UidGenerateService implements UidGenerateServiceI {

	@Autowired
	private UidGenerator uidGenerator;

	@Override
	public Long getUid() {
		return uidGenerator.getUID();
	}

}
