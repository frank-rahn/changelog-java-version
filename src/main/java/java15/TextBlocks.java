package java15;

public class TextBlocks {

  private String html = """
                        <html>
                          <body>
                            <p>Hello "world"!</p>
                          </body>
                        </html>
                        """;

  private String sql = """
                       SELECT *
                         FROM PERSON p \
                         WHERE p.CITY = '%s'  \s
                       """.formatted("Köln");

  public void print() {
    System.out.println(html);
    System.out.println(sql);
  }

  public static void main(String[] args){
    new TextBlocks().print();
  }
}
