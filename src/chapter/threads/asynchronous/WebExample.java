package chapter.threads.asynchronous;

import java.util.concurrent.CompletableFuture;
import static java.util.concurrent.CompletableFuture.*;
import static chapter.threads.threads.Utils.*;

public class WebExample {
    static class ImageLoader {
        public static String loadContent(String url) {
            System.out.println("Fetching "+url);
            delay();
            System.out.println("Got "+url);
            return "A cat";
        }
    }
    public static class Page {
        private String url;
        private String image = null;
        public Page(String url) {
            this.url = url;
        }
        @Override
        public String toString() {
            return "----------- Page: "+url+" Blah blah ["+image+"] blah blah...";
        }
        public Page updateUI() {
            System.out.println(this);
            return this;
        }
        public String getImageURL() {
            return "http://images.com/cat.gif";
        }
        public Page insertImage(String image) {
            return new Page(this.url).setImage(image);
        }
        private Page setImage(String image) {
            this.image = image;
            return this;
        }
    }   
    public static CompletableFuture<Page> fecthPage(String url) {
        return supplyAsync(() -> {
            System.out.println("Fetching "+url);
            delay();
            System.out.println("Got "+url);
            return new Page(url);
        });   
    }
    public static void main(String[] args) throws Exception {
        var p = fecthPage("http://dummy.com/index.html");
        var up = p.thenApplyAsync(Page::updateUI);
        
        var i = p.thenApplyAsync(Page::getImageURL)
            .thenApplyAsync(ImageLoader::loadContent);
        
        var f = p.thenCombineAsync(i,Page::insertImage)
            .thenCombineAsync(up, (page1, page2) -> page1)
            .thenApplyAsync(Page::updateUI);
        
        for (int j=0; j<10; j++) {
            System.out.println("Something else...");
            delay(1000);
        }
        f.join();
        System.out.println(p.get());
        System.out.println(f.get());
    }
}
