import java.text.SimpleDateFormat;
import java.util.Date;

abstract class SystemInfos extends Logger{
    Logger additionalLog;
    @Override
    public void log(){
        additionalLog.log();
    }
}

class DateSystem extends SystemInfos{
    DateSystem(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public void log(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy 'at' HH:mm:ss z");  
        Date date = new Date(System.currentTimeMillis());
        super.addLog(formatter.format(date) + " - ");
        super.additionalLog.log();
    }
}

class OsSystem extends SystemInfos{
    OsSystem(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public void log(){
        super.addLog(System.getProperty("os.name") + " - ");
        super.additionalLog.log();
    }
}

class UserSystem extends SystemInfos{
    UserSystem(Logger logger){ 
        super.additionalLog = logger;
    }

    @Override
    public void log(){
        super.addLog(System.getProperty("user.name") + " - ");
        super.additionalLog.log();
    }
}