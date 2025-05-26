class SecondsAndMinutesChallenge {
    public static void main(String[] args) {
        String result = getDurationString(1680);
        System.out.println(result);
        
        
        result = getDurationString(160,32);
        System.out.println(result);
        
    }
    public static String getDurationString(int seconds)
    {
        if(seconds < 0)
        {
            return "Invalid value entered for seconds parameter.";
        }
        else
        {
            return getDurationString(seconds/60, seconds%60);
        }
    }
    public static String getDurationString(int minutes, int seconds)
    {
        if(minutes < 0 || seconds < 0)
        {
            return "Invalid values entered for minutes and seconds parameter.";
        }
        else
        {
            int hours=minutes/60;
            minutes=minutes%60;
            String s=hours+"h "+minutes+"m "+seconds+"s";
            return s;
        }
    }
    
}