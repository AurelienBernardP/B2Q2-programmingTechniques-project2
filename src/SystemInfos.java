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
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");  
        Date date = new Date(System.currentTimeMillis());
       return super.addLog(formatter.format(date) + " - " +super.additionalLog.log());
        
    }
}

class OsSystem extends SystemInfos{
    OsSystem(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return super.addLog(System.getProperty("os.name") + " - " + super.additionalLog.log());
        
    }
}

class UserSystem extends SystemInfos{
    UserSystem(Logger logger){ 
        super.additionalLog = logger;
    }

    @Override
    public String log(){
        return super.addLog(System.getProperty("user.name") + " - " + super.additionalLog.log());
       
    }
}