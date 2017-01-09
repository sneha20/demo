import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import Utility.GenericUtility;
import javaConstants.Constants;

public class CronJob implements Job {

	private static SchedulerFactory schedFact = new StdSchedulerFactory();

	private static Scheduler sched;

	static {
		try {
			sched = schedFact.getScheduler();
		} catch (SchedulerException e) {
			System.out.println("Exception , Exiting the program " + e);
			System.exit(-1);
		}
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Main called");
		try {
			sched.start();

			JobDetail jobDetail = new JobDetail("MailSend", "Job1",
					CronJob.class);

			ReadPropertiesFile.readConfig();

			CronTrigger trigger = new CronTrigger("sendMailJob",
					"triggerGroup1");

			SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
			Date date = parseFormat.parse(Constants.timeToRun);
			System.out.println(Constants.timeToRun);

			SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm");
			String date1 = displayFormat.format(date);

			String[] timeSplit = date1.split(":");

			trigger.setCronExpression("0 " + timeSplit[1] + " " + timeSplit[0]
					+ " * * ?");
			// trigger.setCronExpression("0/5 * * * * ?");

			sched.scheduleJob(jobDetail, trigger);

		} catch (SchedulerException e) {
			e.printStackTrace();
			System.exit(-1);
		} catch (ParseException e) {
			e.printStackTrace();
			System.exit(-1);
		}

	}

	@Override
	public void execute(JobExecutionContext cntxt) throws JobExecutionException {

		try {

			// scheduled using CRON trigger
			// GenericUtility.callGetOfMail();
			GenericUtility.startServer();

		} catch (Exception ex) {
			System.out.println("Exception occured in execute method " + ex);
		}

	}

	public static void unscheduleCronJob() throws Exception {
		System.out.println("in unschd");
		sched.deleteJob("MailSend", "Job1");
		GenericUtility.callGetOfCronJobHitter();
	}

	

	
}