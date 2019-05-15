abstract class Events extends Logger{
    Logger additionalLog;    
    

}

class CopyEvent extends Events{
    CopyEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return super.addLog(" eventCopy " + super.additionalLog.log());
        
    }
}

class AliasEvent extends Events{
    AliasEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
       return super.addLog(" eventAlias "+super.additionalLog.log());
        
    }
}

class ArchiveEvent extends Events{
    ArchiveEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return super.addLog(" eventArchive "+super.additionalLog.log());
        
    }
}
class FolderEvent extends Events{
    FolderEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return super.addLog(" eventFolder" + super.additionalLog.log());

    }
}

class FileEvent extends Events{
    FileEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return super.addLog(" eventFile "+super.additionalLog.log());
        
    }
}

class DoubleClickEvent extends Events{
    DoubleClickEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
       return super.addLog(" eventDoubleClick " + super.additionalLog.log());
        

    }
}

class ExitEvent extends Events{
    ExitEvent(Logger logger){ 
        super.additionalLog = logger;
    }
    @Override
    public String log(){
        return super.addLog(" eventExit " + super.additionalLog.log());
        
    }
}