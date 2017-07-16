package org.qianrenxi.core.common.utils.time;

import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.ocpsoft.prettytime.PrettyTime;
import org.ocpsoft.prettytime.units.JustNow;
import org.ocpsoft.prettytime.units.Millisecond;
import org.ocpsoft.prettytime.units.Second;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * 日期显示人性化
 * @author jiawei
 *
 */
public class PrettyTimeUtil {

	private final static PrettyTime PRETTY_TIME = new PrettyTime();
	static{
	  PRETTY_TIME.removeUnit(JustNow.class);//去除“片刻之前”
	  PRETTY_TIME.removeUnit(Second.class);// 去除显示秒
	  PRETTY_TIME.removeUnit(Millisecond.class);// 去除显示毫秒
	}
	public static String format(Date date) {
		Locale locale = LocaleContextHolder.getLocale();
		PRETTY_TIME.setLocale(locale);
		// 如果是中文语言，去除结果中的空格字符
		if(locale.getLanguage().equals(new Locale("zh").getLanguage())){
			return StringUtils.remove(PRETTY_TIME.format(date), " ");
		}
		return PRETTY_TIME.format(date);
	} 
}
