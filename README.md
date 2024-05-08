[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/7z0fpYF0)
# T07 Advanced Disjoined

Pada Quiz kali kali ini anda akan melakukan analisis kebali terhadap studi kasus **Academic Simulator** serta mengembangkan sebuah fitur tambahan.

## Rethinking (academic.driver.Driver1, 100pts)

Bila diperhatikan, terdapat sebuah persoalan dari studi kasus Academic Simulator yakni robustness. Perhatikan ilustrasi berikut.

Dalam operasional kegiatan akademik, satu tahun akademik diselenggarakan dalam dua semester reguler (```odd``` dan ```even```) dan sebuah semester antara (```short```). Dalam satu semester dapat dilaksanakan lebih dari satu mata kuliah dan setiap mata kuliah dapat diikuti oleh sekumpulan mahasiswa. Penyelenggaraan perkuliahan suatu mata kuliah pada suatu semester dengan semester lainnya dapat dilakukan oleh dosen atau tim dosen dengan konfigurasi yang dapat berbeda. Sebagai contoh, mata kuliah Operating System, pada pelaksanaan sebelumnya diampu oleh GPS, THS, dan AZP, namun pada pelaksanaan berikutnya diampu oleh GPS dan THS.

Setiap mahasiswa dapat mengikuti beberapa mata kuliah pada suatu semester di suatu tahun akademik. Di akhir semester, mahasiswa akan diberi nilai hasil dari serangkaian asesmen. Bila nilai dirasa kurang memuaskan, mahasiswa dapat mengajukan remedial dengan pemenuhan terhadap syarat dan aturan yang berlaku. Bila nilai akhir dirasa masih kurang memuaskan, mahasiswa dapat mengambil kembali mata kuliah tersebut pada semester mendatang.

Bila hal di atas dapat diantisipasi dengan baik, maka solusi dapat menyajikan sejarah pelaksanaan perkuliahan (```course history```), mulai dari semester dan tahun akademik di mana mata kuliah (```couse```) tersedia untuk pertama kali hingga periode terakhir. Lebih lanjut, untuk setiap periode, juga dapat disajikan daftar mahasiswa (```enrollment```) yang mengikutinya beserta prestasi (```grade```) yang diraihnya.

### Where is the disjoined?

Pada studi kasus sebelumnya, belum secara jelas dijabarkan mengenai relasi antara mata kuliah dan periode pelaksanaannya pada suatu semester di suatu tahun akademik serta kemungkinan perbedaan konfigurasi pengampunya. Hal ini berpotensi pada inkonsistensi solusi. Sebagai contoh, mata kuliah Alsrudat dibuka setiap tahun akademik pada semester Ganjil (```odd```). Pada tahun akademik 2021/2022 diampu oleh GPS dan THS. Tahun akademik mendatang mata kuliah ini akan masih dibuka namun bisa saja oleh pengampu yang berbeda. Perhatikan solusi anda, pada solusi yang baik seharusnya situasi ini tidak mengakibatkan perubahan pada data pelaksanaan mata kuliah di tahun akademik sebelumnya.

### What should be changed?

Berikut adalah daftar operasi yang diperlukan pada solusi:

1. Menambah data dosen (```lecturer```). Fitur ini telah dikembangkan dan tidak ada perubahan yang perlu dilakukan.
2. Menambah data mahasiswa (```student```). Fitur ini telah dikembangkan dan tidak ada perubahan yang perlu dilakukan.
3. Menampilkan data mahasiswa secara rinci (```student detail```). Fitur ini telah dikembangkan dan tidak ada perubahan yang perlu dilakukan.
4. Menambah data mata kuliah (```course```). Fitur ini telah dikembangkan namun perlu dilakukan perubahan, yakni dengan tidak menyertakan tahun akademik, semester pelaksanaan, serta dosen pengampu.
5. Menambah data periode pelaksanaan perkuliahan (```course opening```). Sebuah periode pelaksanaan perkuliahan pada dasarnya merupakan jawaban dari persoalan yang dikemukakan pada awal tugas ini.
6. Menambah data pengambilan mata kuliah (```enrollment```). Fitur ini telah dikembangkan sebelumnya namun perlu dilakukan perubahan relasi. Sebelumnya relasi dilakukan dengan mata kuliah (```course```) kini dengan periode pelaksanaan perkuliahan (```course opening```).
7. Pemberian nilai pada pengambilan mata kuliah (```grading```). Fitur ini telah dikembangkan dan tidak ada perubahan yang perlu dilakukan.
8. Pemberian nilai remedial pada pengambilan mata kuliah (```remedial```). Fitur ini telah dikembangkan dan tidak ada perubahan yang perlu dilakukan.
9. Satu fitur tambahan yang perlu dikembangkan adalah menampilkan sejarah pelaksanaan mata kuliah (```course history```).
10. Sebagai tambahan pada kuis kali ini, anda diminta untuk menambah fitur mencari mahasiswa terbaik (```find the best student```).
11. Kemudian, mahasiswa terbaik tersebut tambahkan ke daftar mahasiswa terbaik (```the best students```).

### 1. Adding New Course (Change).

Terjadi perubahan dalam menambahkan data mata kuliah. Kini, sebuah mata kuliah hanya terdiri dari empat buah data, yakni kode mata kuliah (```code```), nama mata kuliah (```name```), kredit (```credit```), dan nilai minimum (```passing grade```). Dengan demikian, format masukan yang diperlukan menjadi

```bash
course-add#<course-code>#<course-name>#<credit>#<passing-grade>
```

### Adding New Course Opening (New).

Berikutnya, anda perlu mengembangkan fitur untuk menambah data periode pembukaan mata kuliah. Sebuah periode mata kuliah dapat direpresentasikan dengan atribut: kode mata kuliah (```course code```), tahun akademik pelaksanaan (```academic year```), semester (```semester```), serta daftar dosen pengampu (```lecturer list```). Berikut adalah format masukan yang diberikan.

```bash
course-open#<course-code>#<academic-year>#<semester>#<lecturer-list>
```

Perlu diperhatikan bahwa pada dosen pengampu ditulis dalam bentuk inisial, dan dapat saja terdiri dari beberapa inisial untuk mata kuliah yang diampu bersama. Inisial dosen yang satu dengan yang lainnya dipisahkan dengan tanda koma (```,```).

Pembukaan mata kuliah (```course opening```) hanya dapat dilakukan bila mata kuliah (```course```) dan dosen (```lecturer```) terkait sudah terlebih dahulu terdaftar di dalam simulator.

### Find the best student ###
Anda perlu menambkan fitur baru yaitu mencari mahasiswa terbaik berdasarkan performa di semester ganjil dan genap. jika ada terdapat kesamaan diantara mahasiswa, maka yang diambil adalah dengan nim Genap dari semester genap.
```bash
find-the-best-student#<academic-year>#<semester>
```

### Add the Best Student ###
Setelah mendapatkan nama nama mahasiswa terbaik tersebut, tambahkanlah mereka ke dalam list mahasiswa terbaik.
```bash
add-best-student#<best-student>
```

### Course History (New).

Fitur ini digunakan untuk menampilkan sejarah pelaksanaan suatu mata kuliah diurutkan mulai dari pertama kali dibuka. Pada fitur ini juga ditampilkan daftar mahasiswa beserta prestasi yang telah dicapainya pada suatu periode pelaksanaan tertentu. Berikut adalah format perintah yang diberikan.

```bash
course-history#<course-code>
```

Perhatikan contoh berikut.

**Input**:

```bash
lecturer-add#0130058501#Parmonangan Rotua Togatorop#PAT#mona.togatorop@del.ac.id#Information Systems
lecturer-add#0114129002#Iustisia Natalia Simbolon#IUS#iustisia.simbolon@del.ac.id#Informatics
lecturer-add#0124108201#Rosni Lumbantoruan#RSL#rosni@del.ac.id#Information Systems
course-add#12S1101#Dasar Sistem Informasi#3#D
student-add#12S20001#Marcelino Manalu#2020#Information Systems
student-add#12S20002#Yoga Sihombing#2020#Information Systems
student-add#12S20003#Marcel Simanjuntak#2020#Information Systems
course-open#12S1101#2020/2021#odd#IUS
enrollment-add#12S1101#12S20001#2020/2021#odd
enrollment-add#12S1101#12S20002#2020/2021#odd
enrollment-add#12S1101#12S20003#2020/2021#odd
enrollment-grade#12S1101#12S20001#2020/2021#odd#B
enrollment-grade#12S1101#12S20002#2020/2021#odd#B
enrollment-grade#12S1101#12S20003#2020/2021#odd#B
course-open#12S1101#2020/2021#even#IUS
course-open#12S1101#2021/2022#odd#IUS
enrollment-add#12S1101#12S20002#2021/2022#odd
enrollment-add#12S1101#12S20003#2021/2022#odd
enrollment-grade#12S1101#12S20002#2021/2022#odd#AB
enrollment-grade#12S1101#12S20003#2021/2022#odd#AB
enrollment-add#12S1101#12S20001#2020/2021#even
enrollment-add#12S1101#12S20002#2020/2021#even
enrollment-add#12S1101#12S20003#2020/2021#even
enrollment-grade#12S1101#12S20001#2020/2021#even#AB
enrollment-grade#12S1101#12S20002#2020/2021#even#A
enrollment-grade#12S1101#12S20003#2020/2021#even#A
course-history#12S1101
find-the-best-student#2020/2021#odd
find-the-best-student#2020/2021#even
add-best-student#best-student
---

```

**Output**:

```bash
12S1101|Dasar Sistem Informasi|3|D|2020/2021|odd|IUS (iustisia.simbolon@del.ac.id)
12S1101|12S20001|2020/2021|odd|B
12S1101|12S20002|2020/2021|odd|B
12S1101|12S20003|2020/2021|odd|B
12S1101|Dasar Sistem Informasi|3|D|2021/2022|odd|IUS (iustisia.simbolon@del.ac.id)
12S1101|12S20002|2021/2022|odd|AB
12S1101|12S20003|2021/2022|odd|AB
12S1101|Dasar Sistem Informasi|3|D|2020/2021|even|IUS (iustisia.simbolon@del.ac.id)
12S1101|12S20001|2020/2021|even|AB
12S1101|12S20002|2020/2021|even|A
12S1101|12S20003|2020/2021|even|A
0130058501|Parmonangan Rotua Togatorop|PAT|mona.togatorop@del.ac.id|Information Systems
0114129002|Iustisia Natalia Simbolon|IUS|iustisia.simbolon@del.ac.id|Informatics
0124108201|Rosni Lumbantoruan|RSL|rosni@del.ac.id|Information Systems
12S1101|Dasar Sistem Informasi|3|D
12S20001|Marcelino Manalu|2020|Information Systems
12S20002|Yoga Sihombing|2020|Information Systems
12S20003|Marcel Simanjuntak|2020|Information Systems
12S1101|12S20001|2020/2021|odd|B
12S1101|12S20002|2020/2021|odd|B
12S1101|12S20003|2020/2021|odd|B
12S1101|12S20002|2021/2022|odd|AB
12S1101|12S20003|2021/2022|odd|AB
12S1101|12S20001|2020/2021|even|AB
12S1101|12S20002|2020/2021|even|A
12S1101|12S20003|2020/2021|even|A
12S20002|B/A
12S20002|B/A
```

Silakan untuk menganalisis output di atas untuk menemukan perubahan-perubahan format keluaran dari solusi.

## Reporting
Lakukan pertemuan dengan pair anda untuk mendiskusikan persoalan dan pengembangan solusi. Rekaman dari pertemuan tersebut menjadi bagian dari artefak tugas yang harus dikumpulkan. Durasi rekaman minimal **30 menit**.

Presentasikan pekerjaan anda dalam sebuah video. Pada presentasi:
1. Jabarkan solusi anda kelas-per-kelas.
2. Berikan rationale dari setiap implementasi yang anda hasilkan serta berikan argumen mengapa pilihan implementasi anda merupakan pilihan terbaik.
3. Jabarkan alir eksekusi dari solusi anda.
4. Demonstrasikan pekerjaan anda.
5. Tunjukkan hasil yang anda peroleh dari GitHub Classroom.
6. Sebutkan kendala yang dihadapi dalam pengerjaan tugas kali ini.
7. jabarkan bagaimana anda menganalisa penambahan fitur 10 dan 11 tersebut.

Note: Semakin tajam argumen dan penjabaran anda semakin mudah penilaian dilakukan.

Kriteria Video Presentasi:
+ Fullscreen (taskbar terlihat).
+ Suara jernih dan dapat dengan jelas terdengar.
+ Posting video anda di YouTube, di-set "Not For Kids" dengan visibility Unlisted.

## Submission
1. ```src/academic/model/*.java```;
2. ```src/academic/driver/*.java```;
3. changelog.txt

## How to submit?
Please see https://youtu.be/ZOhgmVjWFyo
