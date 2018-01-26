package com.atguigu.springdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

	@Autowired
	private PersonRepsotory personRepsotory;

	public void test() {
		personRepsotory.test();
	}
}
