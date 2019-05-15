abstract class Logger{
    protected String logs="";

    public String getLog(){
        return logs;
    }

    public String addLog(String newLogs){
        System.out.println("Before: "+logs);
       return this.logs = new String(newLogs + this.logs); 
    }

    public abstract void log();
}

class EndLog extends Logger{
    @Override
    public void log(){
        

        super.addLog("---\n");
        System.out.println(super.getLog());
    }
} 