import java.text.SimpleDateFormat;
import java.util.Date;

abstract class SystemInfos extends Logger{
    Logger additionalLog;
}

class DateSystem extends SystemInfos{
    DateSystem(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        //Set the wanted format
        SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");  

        //Convert the given date to the wanted format
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date) + " - " +super.additionalLog.log();
        
    }
}

class OsSystem extends SystemInfos{
    OsSystem(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return System.getProperty("os.name") + " - " + super.additionalLog.log();
        
    }
}

class UserSystem extends SystemInfos{
    UserSystem(Logger logger){ 
        super.additionalLog = logger;
    }

    @Override
    public String log(){
        return System.getProperty("user.name") + " - " + super.additionalLog.log();
       
    }
}