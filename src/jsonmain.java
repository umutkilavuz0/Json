import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class jsonmain {

    private static final String TEK_DOSYA = "harcama.json";
    private static final String LISTE_DOSYA = "harcamalar.json";

    public static void main(String[] args) {
Gson gson = new GsonBuilder()
             .setPrettyPrinting()
             .create();
Expense e1 = new Expense(
		
		 1,
         "Tavuk Dünyası",
         185.50,
         "Yemek",
         "2025-11-29"
		
		);

       KaydetHarcama(gson,e1);

       Expense okunan = okuHarcama(gson);
       System.out.println("Dosyadan okunan tek harcama:");
       System.out.println(okunan);
       
       
       ArrayList<Expense> liste = new ArrayList<>();
       liste.add(e1);
       liste.add(new Expense(2, "Starbucks", 95, "İçecek", "2025-11-28"));
       liste.add(new Expense(3, "Migros Market", 320.75, "Market", "2025-11-27"));

       kaydetListe(gson, liste);

       ArrayList<Expense> okunanListe = okuListe(gson);

       System.out.println("\nDosyadan okunan harcama listesi:");
       if (okunanListe != null) {
           for (Expense e : okunanListe) {
               System.out.println(e);
           }
       }
    }
    



// 1. kısım tek nese
public static void KaydetHarcama(Gson gson, Expense e) {
	
	try(FileWriter a = new FileWriter(TEK_DOSYA)){
		gson.toJson(e,a);
	       System.out.println("Tek harcama dosyaya kaydedildi: " + TEK_DOSYA);
    } catch (IOException ex) {
        System.out.println("Yazarken hata (tek): " + ex.getMessage());
    }
}

public static Expense okuHarcama(Gson gson) {
	try(FileReader b = new FileReader(TEK_DOSYA)){
return gson.fromJson(b,Expense.class);
}
	catch (IOException ex) {
        System.out.println("Okurken hata (tek): " + ex.getMessage());
        return null;
    }
}

// 2 . kısım

private static void kaydetListe(Gson gson, ArrayList<Expense> liste) {
    try (FileWriter writer = new FileWriter(LISTE_DOSYA)) {
        gson.toJson(liste, writer);
        System.out.println("Liste dosyaya kaydedildi: " + LISTE_DOSYA);
    } catch (IOException ex) {
        System.out.println("Yazarken hata (liste): " + ex.getMessage());
    }
}

private static ArrayList<Expense> okuListe(Gson gson) {
    try (FileReader reader = new FileReader(LISTE_DOSYA)) {
        Type type = new TypeToken<ArrayList<Expense>>() {}.getType();
        return gson.fromJson(reader, type);
    } catch (IOException ex) {
        System.out.println("Okurken hata (liste): " + ex.getMessage());
        return null;
    }
}
}
