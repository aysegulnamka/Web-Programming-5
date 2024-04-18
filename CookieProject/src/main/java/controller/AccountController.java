package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import jakarta.servlet.http.HttpSession;

import entities.*;

@WebServlet("/AccountController")
public class AccountController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AccountController() {
       super();
    }

    @SuppressWarnings("unused")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if (action == null) {
            Account account = checkCookie(request);
            if(account == null)
                request.getRequestDispatcher("login.jsp").forward(request, response);
            else {
                // Kullanici remember me ile oturum acmis
                response.sendRedirect("welcome.jsp"); // welcome.jsp'ye yonlendir.
            }
        } 
        else {
            if (action.equalsIgnoreCase("logout")) { //welcome.jsp den cikis saglar.
                logout(request, response);          
            }
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        if(action == null) {
            String username = request.getParameter("username").trim(); //parametreden gelen bosluklari kaldirmak icin
            String password = request.getParameter("password").trim();
            boolean remember = request.getParameter("remember") != null;

            if (isValidAccount(username, password)) {
                if(remember) { // cookie kullanici adi ve parolayi sadece 120 saniyeligine hatirlayacak
                    Cookie ckUsername = new Cookie ("username", username);
                    ckUsername.setMaxAge(120);
                    response.addCookie(ckUsername);
                    Cookie ckPassword = new Cookie ("password", password);
                    ckPassword.setMaxAge(120);
                    response.addCookie(ckPassword);
                }
                session.setAttribute("username", username); 
                response.sendRedirect("welcome.jsp"); // Giris basarili welcome.jsp'ye yonlendir.
            } else {
                request.setAttribute("error", "Not Invalid"); // Giris hatali ise error verir.
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } 
        else {
        	
        }
    }

    private Account checkCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        Account account = null;
        if (cookies == null)
            return null;
        else {
            String username = "", password = "";
            for (Cookie ck : cookies) {
                if (ck.getName().equalsIgnoreCase("username"))
                    username = ck.getValue();
                if(ck.getName().equalsIgnoreCase("password"))
                    password = ck.getValue();
            }
            if(!username.isEmpty() && !password.isEmpty())
                account = new Account(username, password);
        }
        return account;
    }

    private boolean isValidAccount(String username, String password) {
        // Kullanici adi ve parola kontrolu
        return true; 
    }

    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.removeAttribute("username");
        Cookie[] cookies = request.getCookies();
        for (Cookie ck : cookies) {
            if(ck.getName().equalsIgnoreCase("username")) {
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
            if(ck.getName().equalsIgnoreCase("password")) {
                ck.setMaxAge(0);
                response.addCookie(ck);
            }
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }
}
