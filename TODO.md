# MyPhoneBook
---
Aplikasi ini masih dalam tahap awal (alpha) masih banyak fitur yang akan ditambahkan dan masih banyak bug yang belum terdeteksi. berikut adalah beberapa hal yang akan ditambah/diubah/diperbaiki.

### Bugs Yang di Ketahui
Update : `0.2.05` - _`Alpha`_ 
- **`MAJOR`** : Menggunakan nama yang sama akan menimbulkan data yang _corrupted_ dan/atau tertimpa yang menyebabkan tampilan _Tree_ Error.
- Dalam suatu kondisi, warna _background_ tertukar dengan _foreground_ _TextField_ dalam _Frame_ `Tambah` dan `Edit`. tetapi ini tidak dapat satu instruksi yang menyebabkan kondisi ini. Penyebabnya mungkin dari _API JSwing_.
- Setelah melakukan penghapusan data yang berada di akhir suatu _sub-node_ kategori dan sebelum _sub-node_ kategori lain, ketika di `Refresh` akan melakukan penutupan (_collapse_).\

### Fitur Yang akan di Tambah
- User Interface yang lebih baik.
- Seleksi pemilihan tempat penambahan strip pada nomor Handphone.
