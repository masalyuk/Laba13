
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nikit
 */
public class AddressBook implements Iterable<User> {

    private HashSet<User> users = new HashSet<>();
    public AddressBook(String file)
    {
        readFile(file);
    }
    public AddressBook()
    {
    }
    public synchronized void addUser(String name, String number)
    {
        
        User user = new User(name,number);
        if(!users.contains(user))
            users.add(user);
    }
    
    public synchronized void editNumber(String name, String old_number, String new_number)
    {
        for(User user : users)
        {
            if(user.getName().equals(name))
            {
                System.out.println("Nashli user");
                ArrayList<String> nums = user.getListNumbers();
                
                int index = 0;
                for(String number : nums)
                {
                    if(number.equals(old_number))
                    {
                        System.out.println("Nashli nomer");
                        nums.set(index, new_number);
                    }
                    index++;
                }
                break;
            }
        }
    }
    
    
    public synchronized void addNumber(String name, String number)
    {
        for(User user : users)
        {
            if(user.getName().equals(name))
            {
                user.addNumber(number);
                break;
            }
        }
    }
    public Iterator iterator()
    {
        return users.iterator();
    }

    public int size()
    {
        return users.size();
    }
    public void wtiteToFile(String fileName)
    {
        FileWriter writer;
        try {
            writer = new FileWriter(fileName, false);
            for(User user : users)
            {
                writer.write(user.getName() + " " + user.getNumbers() + "\r\n");
                writer.flush();
            }
        } catch (IOException ex) {
           System.out.println("WriteToFile: "+ex.getMessage());
        }
    }
    
    public void readFile(String fileName) 
    {

        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String str;
            while((str = reader.readLine())!= null)
            {
                String[] info = str.split(" ");
                User user = new User(info[0]);
                
                for(int i = 1; i < info.length; i++)
                    user.addNumber(info[i]);
                
                users.add(user);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }
         catch (IOException ex) {
            Logger.getLogger(AddressBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String toString()
    {
        StringBuffer usersInfo = new StringBuffer();
        for(User user : users)
        {
           usersInfo.append(user.toString()).append("\n"); 
        }

        return usersInfo.toString();
    }
    
}
