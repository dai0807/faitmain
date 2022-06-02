package com.faitmain.domain.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.faitmain.domain.product.domain.Inquiry;
import com.faitmain.domain.product.mapper.InquiryMapper;

@Service("inquiryServiceImpl")
public class InquiryServiceImpl implements InquiryService {

	@Autowired
	private InquiryMapper inquiryMapper;
	
	@Override
	public void addInquiry(Inquiry inquiry) throws Exception {
		inquiryMapper.addInquiry(inquiry);
	}

	@Override
	public Inquiry getInquiry(int inquiryNumber) throws Exception {
		return inquiryMapper.getInquiry(inquiryNumber);
	}

	@Override
	public Map<String, Object> getInquiryList(Map<String, String> map) throws Exception {
		
		List<Inquiry> list = inquiryMapper.getInquiryList(map);
		int totalCount = inquiryMapper.getTotalCount(map);
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		resultMap.put("list", list);
		resultMap.put("totalCount", new Integer(totalCount));
				
		return resultMap;
	}

	@Override
	public void updateInquiry(Inquiry inquiry) throws Exception {
		inquiryMapper.updateInquiry(inquiry);		
	}

	@Override
	public void deleteInquiry(int inquiryNumber) throws Exception {
		inquiryMapper.deleteInquiry(inquiryNumber);		
	}

}