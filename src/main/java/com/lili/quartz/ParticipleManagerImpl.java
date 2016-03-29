package com.lili.quartz;

import org.springframework.stereotype.Service;

@Service("participleManager")
public class ParticipleManagerImpl implements ParticipleManager{
	public void participleContent() {
		System.out.println("一分钟输出一次");
	}
}
