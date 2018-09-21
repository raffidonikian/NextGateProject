package ng.Entity;

public class Album {
    private int albumId;
    private String singerName;
    private String albumName;
    private String releaseYear;
    private String recordCompany;

    public Album(int albumId, int singerId, String albumName, String releaseYear, String recordCompany) {
        this.albumId = albumId;
        this.albumName = albumName;
        this.singerName = ng.DAO.SingerDao.getSingerNameById(singerId);
        this.releaseYear = releaseYear;
        this.recordCompany = recordCompany;

    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getRecordCompany() {
        return recordCompany;
    }

    public void setRecordCompany(String recordCompany) {
        this.recordCompany = recordCompany;
    }
}
