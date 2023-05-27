package classes;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

public class GetCookie {
	public static String GetCookie(HttpServletRequest request,String NameCookie)
	{
		Cookie[] cookies = request.getCookies();
        Cookie cookie = null;
        if(cookies !=null) 
        {
            for(Cookie c: cookies) 
            {
                if(NameCookie.equals(c.getName())) 
                {
                    return c.getValue();
                    
                }
            }
        }
        return null;
	}
}
