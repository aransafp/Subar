# Subar
Subar adalah aplikasi portal berita yang menyediakan beritan populer tiap harinya. Data didapatkan dari public API <strong>News API</strong>


## Features

- List article, menampilkan article berita terpopuler tiap harinya.
- Detail article, menampilkan isi berita.
- Favorit article, menampilkan berita favorit pengguna.
- Daily remainder, mengirimkan notifikasi kepada pengguna setiap jam 7 pagi untuk membaca berita.

Screenshots
-----------
<img src="screenshots/subar.gif" alt="screenshots" width="250"><br><br>

## Implementation
- Modular architecture
- Clean architecture (separate 3 layer: presentation, domain, data)
- <strong>Coroutine Flow</strong> for reactive programming
- <strong>Koin</strong> for dependency injection
- <strong>Retrofit</strong> for fetching data from network
- <strong>Room</strong> for local data
- Alarm manager
- Notification