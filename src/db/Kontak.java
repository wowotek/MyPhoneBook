package db;

public class Kontak
{

    private console.ConsoleUtility cu = new console.ConsoleUtility(true, true);

    private String Nama;
    private String NoHP;
    private String Kategori;

    public Kontak(String Nama, String NoHP, String Kategori)
    {
        this.Nama = Nama;
        this.NoHP = NoHP;
        this.Kategori = Kategori;
    }

    public String getNama()
    {
        return Nama;
    }

    public String getNoHP()
    {
        return NoHP;
    }

    public String getNoHP(boolean i)
    {

        return this.cu.parseHP(NoHP);
    }

    public String getKategori()
    {
        return Kategori;
    }

    public void setNama(String Nama)
    {
        this.Nama = Nama;
    }

    public void setNoHP(String NoHP)
    {
        this.NoHP = NoHP;
    }

    public void setKategori(String Kategori)
    {
        this.Kategori = Kategori;
    }
}
