/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author nikit
 */
public class AddressBookServlet extends HttpServlet {

   private AddressBook book;
   
    private String prevName;
    private String prevNumber;
    
    
   @Override
    public void init(ServletConfig config) {
         book = new AddressBook("D:\\Учеба\\Laba\\WebApplication1\\src\\java\\book.txt");

        }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       PrintWriter out = response.getWriter();
        out.println("<html><body>"); 
       
        request.setCharacterEncoding("windows-1251");
       response.setContentType("text/html;charset=windows-1251");
       request.getRequestDispatcher("newjsp.jsp").include(request, response);

       
       String name_users = request.getParameter("name_users");
       String number_users = request.getParameter("number_users");
       String name_number = request.getParameter("name_number");
       String number_number  = request.getParameter("number_number");
       
       String name  = request.getParameter("name");
       String old_number  = request.getParameter("old_number");
       String new_number  = request.getParameter("new_number");
       
      
        if(name_users != null && !name_users.equals(prevName))
        {
            addUser(name_users,  number_users);
            prevName = name_users;
            prevNumber = number_users;
        } 
        else if(name_number != null && !(name_number.equals(prevName) && 
                number_number.equals(prevNumber)))
         {
            addNumber(name_number, number_number); 
            prevName = name_number;
            prevNumber = number_number;
         }
        else if(old_number != null)
        {
            editNumber(name,old_number, new_number);
        }
        
       showBook(out);
       
       book.wtiteToFile("D:\\Учеба\\Laba\\WebApplication1\\src\\java\\book.txt");
       
       out.println("</html></body>");
    }
    
    public void showBook(PrintWriter out)
    {
        for(User user : book)
            out.println("<p>"+user+"</p>");
    }
    public void addUser(String name, String number)
    {
        book.addUser(name, number);
    }
    public void addNumber(String name, String number)
    {
        book.addNumber(name, number);
    }
    public void editNumber(String name, String old_number, String new_number)
    {
        book.editNumber(name, old_number, new_number);
    }
}
