
import java.util.ArrayList;
import java.util.Objects;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nikit
 */
public class User {
    private String name;
    private ArrayList<String> numbers = new ArrayList<>();
    
    
    public User(String name,String number)
    {
        this.name = name;
        addNumber(number);
    }
    
    public User(String name)
    {
        this.name = name;
    }
    
    public void addNumber(String number){numbers.add(number);}

    public String getName(){return name;}
    public ArrayList<String> getListNumbers()
    {
        return numbers;
    }
    public String getNumbers()
    {
        StringBuffer nums = new StringBuffer();
        for(String number : numbers)
            nums.append(number + " ");
        
        return nums.toString();
    }
    
    public String toString()
    {
        StringBuffer info = new StringBuffer(name);
        info.append(": ");
        info.append(getNumbers());
//            ?for(String number : numbers)
//                info.append(number + " ");

        return info.toString();       
    }
    
    public boolean equals(Object o)
    {
        User user = (User)o;
        if(name.equals(user.name))
            return true;
        
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.name);
        return hash;
    }
    
}
