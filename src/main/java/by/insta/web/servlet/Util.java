package by.insta.web.servlet;

import by.insta.Constant;
import by.insta.entity.Post;

import java.util.List;

public class Util {

    public static List<Post> getPage(int start, List<Post> posts){
        if (posts.isEmpty()){
            return posts;
        }
        int end = start;
        for (int i = start; i < posts.size(); i++) {
            if (end == start + Constant.PAGES){
                break;
            }
            end++;
        }
        return posts.subList(start, end);
    }

    public static int getCountPages(List<Post> posts){
        if (posts.isEmpty()){
            return 1;
        }
        return ((posts.size() - 1) / Constant.PAGES) + 1;
    }

    public static boolean isEmpty(String string){
        return string.trim().isEmpty();
    }
}
