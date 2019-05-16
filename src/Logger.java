abstract class Logger{


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
        
        return "\n";
    }
} 