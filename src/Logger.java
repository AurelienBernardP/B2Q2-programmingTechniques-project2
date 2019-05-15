abstract class Logger{
    protected String logs="";

    public String getLog(){
        return logs;
    }

    public String addLog(String newLogs){
       return this.logs = new String(newLogs + this.logs); 
    }

    public abstract String log();
}

class EndLog extends Logger{
    @Override
    public String log(){
        

        return super.addLog("---\n");
    }
} 