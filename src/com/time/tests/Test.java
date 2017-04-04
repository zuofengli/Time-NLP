package com.time.tests;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.time.nlp.TimeNormalizer;
import com.time.nlp.TimePoint;
import com.time.nlp.TimeUnit;
import com.time.nlp.stringPreHandlingModule;
import com.time.util.DateUtil;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		// TODO Auto-generated method stub
		String path = TimeNormalizer.class.getResource("").getPath();
		String classPath = path.substring(0, path.indexOf("/com/time"));
		System.out.println(classPath+"/TimeExp.m");
//		TimeNormalizer normalizer = new TimeNormalizer(classPath+"/TimeExp.m");
//		TimeNormalizer normalizer = new TimeNormalizer(classPath+"/TimeExp.m", false);

		TimeNormalizer normalizer = new TimeNormalizer(classPath+"/TimeExp.m", false);
		
		String timeBase = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(Calendar.getInstance().getTime());
		
		normalizer.setTimeBase(timeBase);
		normalizer.setOldTimeBase(timeBase);
		System.out.println(normalizer.getTimeBase());
		
		int rpointer = 5;
		String[] temp = new String[rpointer];
		temp[4] = "上月一天";
		temp[1] = "今年一月";
		temp[2] = "2004年";
//		temp[3] = "既往史：乙肝40年。";
		temp[3] = "2009年起曾服用索拉菲尼抗肿瘤治疗2年，现已停药。";
		temp[0] = "近9月";
//		temp[4] = "9个月前";
		TimeUnit[] Time_Result = null;
		Time_Result = new TimeUnit[rpointer];
		// System.out.println("Basic Data is " + timebase);
		/**时间上下文： 前一个识别出来的时间会是下一个时间的上下文，用于处理：周六3点到5点这样的多个时间的识别，第二个5点应识别到是周六的。*/
		TimePoint contextTp = new TimePoint();
		for (int j = 0; j < rpointer; j++) {
			System.out.println("44 - " + temp[j]);
			String target = temp[j];
			target = stringPreHandlingModule.delKeyword(target, "\\s+"); // 清理空白符
			target = stringPreHandlingModule.delKeyword(target, "[的]+"); // 清理语气助词
			target = stringPreHandlingModule.numberTranslator(target);// 大写数字转化
			
			
			Time_Result[j] = new TimeUnit(target, normalizer, contextTp);
			System.out.println(DateUtil.formatDateDefault(Time_Result[j].getTime()) + "-" + Time_Result[j].getIsAllDayTime());
			contextTp = Time_Result[j]._tp;
		}
		Time_Result = normalizer.filterTimeUnit(Time_Result);
		for(int i=0; i<Time_Result.length; i++){
			System.out.println(DateUtil.formatDateDefault(Time_Result[i].getTime()) + "-" + Time_Result[i].getIsAllDayTime());
		}
		
		
		/**过滤无法识别的字段*/


		
		

		normalizer.parse("Hi，all。下周一下午三点开会");// 抽取时间
		TimeUnit[] unit = normalizer.getTimeUnit();
		System.out.println(unit);
		
		String exp = "上月一天";
//		String exp = "今年一月";
//		String exp = "患者2003年体检时B超提示肝硬化门脉高压，2004年进行了";
//		String exp = "家庭住址 上海  亲友姓名及地址 俞滔,虹中路460弄51号401室  工作单位  入院日期 2007-12-06 15:23     记录日期207-12-06 15:00:00　　　供史者　　本人（可靠） 【主诉】一月前呕血800ml一次伴乏力。 【现病史】　患者2003年体检时B超提示肝硬化门脉高压，肝脏有结节。当时无呕血便血，无其他不适，今年一月复查B超发现肝右叶原发性ca，在本院行肝右叶切除术。术后予化疗（具体不祥）。上月一天突然呕血800ml左右在本院消化科保守治疗成功。MRI提示肝硬化门脉高压食道静脉曲张。查三系减少凝血功能正常。发现发病以来，患者精神夜眠可，胃纳两便可，体重无明显改变。无黄疸无明显腹水。 【既往史】否认肝炎、结核、血吸虫病等传染病史，但是Hbsag(+) 否认糖尿病高血压史。无外伤史。 无药物食物过敏史 按计划进行接种 【系统回顾】无呼吸系统病史； 无循环系统病史；无其他消化系统病史； 无泌尿生殖系统病史； 无血液系统病史； 无内分泌系统病史；无神经精神系统病史； 无运动骨骼系统病史； 【个人史】生长于原籍，否认抽烟、喝酒等不良嗜好，否认疫区驻留史。 【婚姻史】已婚，已育。 【家族史】否认家族遗传病患者，否认家族传染病患者，父母子女均健康。 【体格检查】 T：37℃　P：80次/分 R：20次/分 BP：120/80mmHg  神志清晰，精神尚可，呼吸平稳，营养中等，表情自如，发育正常，自主体位，应答流畅，查体合作。无恶液质，全身皮肤无黄染，无肝掌、蜘蛛痣。全身浅表淋巴结无肿大?SPAN style=\"BACKGROUND: white\" TOKEN=\"ISLAND\" oldColor SID=\"{7eb1ec67-aa11-4b99-ade8-77b25a16e0c0}\" DID=\"{46334F8E-A4A9-4FF2-9CA8-91E3E8A04C61}\" IsMustInput=\"true\">头颅无畸形，巩膜无黄染、眼球无突出、瞳孔等大等圆、对光反射灵敏，听力正常、外耳道无分泌物、耳廓、乳突无压痛鼻中隔无偏曲、鼻翼无扇动、鼻窦区无压痛口唇红润光泽、口腔无特殊气味、伸舌居中、扁桃体无肿大、腮腺正常。颈软，甲状腺未及肿大，胸廓无畸形，双肺呼吸音清，未及干湿罗音。心前区无隆起，心界不大，心率75次/分，律齐，各瓣膜区未及病理性杂音。腹部检查见本科检查。肛门及生殖器未检，四肢脊柱无畸形，活动自如，神经系统检查(－)。  【本科检查】 腹部平软，未见腹壁静脉曲张，无压痛及反跳痛，肝脾肋下未及，肝肾区无叩击痛，肠鸣音4-6次/分，移动性浊音（－），双下肢不肿。 【辅助检查】 血常规：WBC：1.6×10^9/L, RBC:2.82×10^12/L, Hb:59g/L, Plt:77×10^9/L病毒检测：HBsAb（+），MRI：肝硬化，脾大，腹腔积液，门脉高压，食管静脉曲张，右肾小结石少量腹水。B超：肝硬化，少量腹水，脾肿大胃镜：未做。        入院诊断      肝硬化门脉高压";
		normalizer.parse(exp);
//		normalizer.parse("本周日到下周日出差");// 多时间识别
		unit = normalizer.getTimeUnit();
		System.out.println("66" + exp);

		for(int i=0; i<unit.length; i++){
			
			System.out.println(DateUtil.formatDateDefault(unit[i].getTime()) + "-" + unit[i].getIsAllDayTime());
		}
		
		
	}

}
