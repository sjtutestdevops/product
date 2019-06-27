package com.devops.products.service;

import java.util.Date;
import java.util.Calendar;
import java.util.List;

import com.devops.products.mapper.MemoMapper;
import com.devops.products.model.Memo;
import com.devops.products.model.MemoExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoService {

	@Autowired
	private UserService userService;
	@Autowired
	private MemoMapper memoMapper;

	public String setMemo(String product_name, String user_name, Integer exp) {
		try {
			Integer user_id = userService.getUserId(user_name);

			Memo memo = new Memo();
			memo.setProductName(product_name);
			memo.setUserId(user_id);
			memo.setExp(exp);
            memoMapper.insertSelective(memo);

            return "Success";
        } catch (Exception e) {
            return e.getMessage();
        }
	}
	
	public List<Memo> getUserMemo(String user_name) {
		Integer user_id = userService.getUserId(user_name);

		MemoExample memoExample = new MemoExample();
		memoExample.createCriteria().andUserIdEqualTo(user_id);
		return memoMapper.selectByExample(memoExample);
	}
	
	public String isExpired(Integer memo_id) {
		Memo memo = memoMapper.selectByPrimaryKey(memo_id);
		if (memo != null) {
			Date start_time = memo.getStartTime();
			Date current_time = new Date();

			Calendar cal_start = Calendar.getInstance();
			Calendar cal_curr = Calendar.getInstance();
			cal_start.setTime(start_time);
			cal_curr.setTime(current_time);

			int days = ((int) (cal_curr.getTime().getTime() / 1000) - (int) (cal_start.getTime().getTime() / 1000)) / 3600 / 24;
			System.out.println(days);

			Integer exp = memo.getExp();
			if (days > exp*31) {
				return "过期了";
			}
			return "还可使用";
		}
		return "不存在该备忘";
	}
}
