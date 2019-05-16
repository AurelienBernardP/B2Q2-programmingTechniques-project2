abstract class Logger{
    protected String logs="";

    /**
     * Add the given string "newLogs" at the beginning of the string "logs" of the instance.
     * @param newLogs :a string given to the current instance
     * @return : "logs" from this instance concatenated to "newLogs" as a string
     * 
     */
    public String addLog(String newLogs){
       return this.logs = new String(newLogs + this.logs); 
    }

    /**
     * Add an element, based on the instance from which it is called, at the beginning of the 
     * string "logs" of this instance .
     * @return : a new "logs" from this instance 
     * 
     */
    public abstract String log();
}

class EndLog extends Logger{
    @Override
    public String log(){
        

        return super.addLog("\n");
    }
} 