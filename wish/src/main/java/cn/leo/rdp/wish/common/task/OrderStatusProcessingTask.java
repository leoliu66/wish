package cn.leo.rdp.wish.common.task;

import cn.leo.rdp.common.task.job.BaseJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author 刘露leo
 *
 */
@Component
public class OrderStatusProcessingTask implements BaseJob{
	private Logger logger = LoggerFactory.getLogger(getClass());

		
	@Override
	public String execute(Map<String, Object> params) {
		Date now = new Date();
		Date createTime = new Date(now.getTime() - 60000*10); //10分钟前的时间
		Date endTime = new Date(now.getTime() - 60000*60*24); //一天前的时间
		return "success";
	}

}
