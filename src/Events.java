abstract class Events extends Logger{
    Logger additionalLog;    
    

}

class CopyEvent extends Events{
    CopyEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return " eventCopy " + super.additionalLog.log();
        
    }
}

class AliasEvent extends Events{
    AliasEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
       return " eventAlias "+super.additionalLog.log();
        
    }
}

class ArchiveEvent extends Events{
    ArchiveEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return " eventArchive "+super.additionalLog.log();
        
    }
}
class FolderEvent extends Events{
    FolderEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return " eventFolder" + super.additionalLog.log();

    }
}

class FileEvent extends Events{
    FileEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return " eventFile "+super.additionalLog.log();
        
    }
}

class DoubleClickEvent extends Events{
    DoubleClickEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
       return " eventDoubleClick " + super.additionalLog.log();
        

    }
}

class ExitEvent extends Events{
    ExitEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return " eventExit " + super.additionalLog.log();
        
    }
}