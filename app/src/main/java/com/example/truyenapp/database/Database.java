package com.example.truyenapp.database;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.truyenapp.model.Comment;
import com.example.truyenapp.model.Chapter;
import com.example.truyenapp.model.Evaluate;
import com.example.truyenapp.model.RewardPoints;
import com.example.truyenapp.model.RewardExchange;
import com.example.truyenapp.model.ReadingHistory;
import com.example.truyenapp.model.ModelSearch;
import com.example.truyenapp.model.ContentOfChapter;
import com.example.truyenapp.model.ClassifyStory;
import com.example.truyenapp.model.Account;
import com.example.truyenapp.model.Notification;
import com.example.truyenapp.model.Statistical;
import com.example.truyenapp.model.Story;
import com.example.truyenapp.model.Item;

import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {

    public static final String DBNAME= "ArgonComic.db";
    public Database(Context context) {
        super(context, "ArgonComic.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // Tài khoản
        db.execSQL("CREATE TABLE if not exists taikhoan (\n" +
                "    id         INTEGER        PRIMARY KEY AUTOINCREMENT\n" +
                "                              NOT NULL,\n" +
                "    email      CHAR (250)     NOT NULL,\n" +
                "    matkhau    CHAR (250)     NOT NULL,\n" +
                "    hoten      NVARCHAR (250),\n" +
                "    dienthoai  CHAR (11),\n" +
                "    diemthuong INTEGER        DEFAULT (0) \n" +
                "                              NOT NULL,\n" +
                "    loaitk     INTEGER        NOT NULL\n" +
                "                              DEFAULT (0),\n" +
                "    linkanh    TEXT\n" +
                ");\n");
        // insert taikhoan
        db.execSQL("INSERT INTO taikhoan (id, email, matkhau, hoten, dienthoai, loaitk, linkanh) VALUES (1, 'admin@gmail.com', 'admin@123', 'Quản trị viên', '0392382167', 1, NULL);");
        db.execSQL("INSERT INTO taikhoan (id, email, matkhau, hoten, dienthoai, loaitk, linkanh) VALUES (2, 'vutruong@gmail.com', 'vu@123', 'Quản trị viên', '0392382167', 1, 'https://vi.pngtree.com//freepng/gray-cartoon-cat-cat-cartoon-animals-gray_3925392.html ');");
        db.execSQL("INSERT INTO taikhoan (id, email, matkhau, hoten, dienthoai, loaitk, linkanh) VALUES (3, 'user1@gmail.com', 'user1@123', 'Người dùng 2', NULL, 0, NULL);");
        db.execSQL("INSERT INTO taikhoan (id, email, matkhau, hoten, dienthoai, loaitk, linkanh) VALUES (4, 'user2@gmail.com', 'user2@123', 'Người dùng 3', NULL, 0, NULL);");


        // Truyện
        db.execSQL("CREATE TABLE if not exists truyen (\n" +
                "    id         INTEGER        PRIMARY KEY AUTOINCREMENT\n" +
                "                              NOT NULL,\n" +
                "    tentruyen  NVARCHAR (250) NOT NULL,\n" +
                "    tacgia     NVARCHAR (250),\n" +
                "    mota       TEXT           NOT NULL,\n" +
                "    theloai    NVARCHAR (250) NOT NULL,\n" +
                "    linkanh    TEXT           NOT NULL,\n" +
                "    trangthai  INTEGER        DEFAULT (0) \n" +
                "                              NOT NULL,\n" +
                "    key_search CHAR (250)     NOT NULL\n" +
                ");");
        //insert truyen
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (1, 'MAOU NI NATTE NODE, DUNGEON TSUKUTTE JINGAI MUSUME TO HONOBONO SURU', 'Đang cập nhật', 'Vào một ngày đẹp trời, tôi thức dậy và đột nhiên trở thành Quỷ vương, người mang trách nhiệm bảo vệ dungeon. Ở một thế giới tràn ngập bởi bạo lực. Những con quái khổng lồ ở khắp nơi, đấu tranh hằng ngày để sinh tồn. Tôi không còn lựa chọn nào khác ngoài tăng thêm sức mạnh phòng thủ để có thể tiếp tục sống—hoặc đó là những gì tôi nghĩ.', 'Fantasy', 'https://st.nettruyenplus.com/data/comics/249/maou-ni-natte-node-dungeon-tsukutte-jing-7696.jpg', 0, 'ma vuong ham nguc');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (2, 'Biên Niên Sử Quý Tộc Tái Sinh Ở Thế Giới Khác', 'Đang cập nhật', 'Shiina Kazuya, đã chết trong cố gắng bảo vệ một người bạn thời thơ ấu. Cậu đã được tái sinh thành Kain Von Shilford, con trai thứ 3 trong một gia đình quý tộc. Cuộc phiêu lưu của cậu bắt đầu trong thế giới kiếm và ma thuật bắt đầu...', 'Harem', 'https://cdn.myanimelist.net/images/anime/1071/135255.jpg', 0, 'chuyen sinh quy toc');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (3, 'TA MUỐN TRỞ THÀNH CHÚA TỂ BÓNG TỐI!', 'Đang cập nhật', 'Tôi là một cậu trai ước mơ làm \"chúa tể bóng tối\", chèo lái câu chuyện trong bóng tối, bí mật phô bày năng lực, tên là Shido. Vừa mới hớn hở vì được chuyển sinh đến thế giới khác và quyết định \"mình sẽ là chúa tể bóng đêm, ngấm ngầm loại bỏ giáo phái đen tối\", thì hình như Shido lại tái sinh trong đúng cái giáo phái ấy?? Bộ truyện tranh hài nghiêm túc CỰC MẠNH nói về tổ chức bóng tối Ánh Viên đang dần mở rộng do Shido lãnh đạo bắt đầu!', 'Action', 'https://cdn.myanimelist.net/images/anime/1874/121869.jpg', 0, 'chua te bong toi');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (4, 'MASHLE: MUSCLES AND MAGIC', 'Đang cập nhật', 'Có một thế giới nơi phép thuật có thể chi phối mọi thứ nhưng sâu trong rừng có một thanh niên dành thời gian để luyện tập cơ bắp. Mặc dù không thể sử dụng phép thuật, nhưng cậu ta có một cuộc sống yên bình với cha mình...', 'Action', 'https://m.media-amazon.com/images/M/MV5BM2M1Yzc5OTMtNWQxYS00NTg5LThiYjQtODRhZGMwODVkNjAyXkEyXkFqcGdeQXVyMTEzMTI1Mjk3._V1_.jpg', 0, 'ma thuat co bap');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (5, 'THƯƠNG NGUYÊN ĐỒ', 'Đang cập nhật', 'Ta tên Mạnh Xuyên, năm nay mười lăm tuổi, là Đông Ninh phủ \"Kính Hồ đạo viện\" đương đại đại sư huynh.', 'Cổ Đại', 'https://1.bp.blogspot.com/-SLCDUcM1Dms/XkE5Z-qDeVI/AAAAAAAAamw/IO9FQarYDeMirdRuw3-NP3FScbBe7CqSACLcBGAsYHQ/s0/Thuong-Nguyen-Do-Chapter-3-P-7.jpg', 0, 'thuong nguyen do');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (6, 'THẦN HỒN VÕ ĐẾ', 'Đang cập nhật', 'Truyện tranh Thần Hồn Võ Đế được cập nhật nhanh và đầy đủ nhất tại Argon Comic. Bạn đọc đừng quên để lại bình luận và chia sẻ, ủng hộ Argon Comic ra các chương mới nhất của truyện Thần Hồn Võ Đế.', 'Cổ Đại', 'https://st.nhattruyenmin.com/data/comics/132/hoang-tu-bach-tuyet-da-bi-hoang-hau-bat-714.jpg', 0, 'than hon vo de');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (7, 'LĂNG THIÊN THẦN ĐẾ', 'Đang cập nhật', 'Truyện tranh Lăng Thiên Thần Đế được cập nhật nhanh và đầy đủ nhất tại NetTruyen. Bạn đọc đừng quên để lại bình luận và chia sẻ, ủng hộ NetTruyen ra các chương mới nhất của truyện Lăng Thiên Thần Đế.', 'Cổ Đại', 'https://st.nettruyento.com/data/comics/179/xam-nhap-vao-truong-trung-hoc-tai-phiet.jpg', 0, 'lang thien than de');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (8, 'TU LA KIẾM TÔN', 'Đang cập nhật', 'Truyện tranh Tu La Kiếm Tôn được cập nhật nhanh và đầy đủ nhất tại NetTruyen. Bạn đọc đừng quên để lại bình luận và chia sẻ, ủng hộ NetTruyen ra các chương mới nhất của truyện Tu La Kiếm Tôn.', 'Cổ Đại', 'https://truyentiki.com/a/img/str/src/51292.jpg', 0, 'tu la kiem ton');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (9, 'KHOA KỸ ĐẠI TIÊN TÔNG', 'Đang cập nhật', 'Vốn là học bá IQ cao thời hiện đại, bậc thầy chế tạo thủ công, bởi vì thức đêm làm việc quá sức mà chết, xuyên không đến thế giới tu tiên huyền nguyên đại lục nhập vào thân thể một tên phú nhị đại trong một gia tộc nghèo Tô Cảnh Nghiêu dựa vào tri thức hiện đại, kết hợp với bàn tay vàng bật hack để tu tiên, phi thăng cấp tốc, ở tu tiên giới mở ra những nghiên cứu về sản phẩm khoa học kỹ thuật chấn kinh ánh mắt của đám tu tiên giả, tạo ra một thời đại mới, trở thành đại lão, cùng các mỹ nữ đi lên đỉnh phong nhân sinh Ở học viện đọ sức cùng thiên tài, trở thành nhất bảng top 100; Dẫn đầu học viện tham gia tranh bá, bản thân trở thành đệ nhất nhân của nam vực; lãnh đạo tông môn đánh boss, cuối cùng trở thành truyền thuyết đại lục', 'Harem', 'https://hitruyen.vip/wp-content/uploads/2021/09/khoa-ky-dai-tien-tong-hitruyenvip.jpg', 0, 'khoa ky dai tien tong');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (10, 'VÕ THẦN CHÚA TỂ', 'Đang cập nhật', 'Thiên Vũ đại lục nhất đại truyền kỳ Tần Trần, bởi vì bạn tốt phản bội bất ngờ ngã xuống Võ vực. 300 năm sau, hắn chuyển sinh ở một cái nhận hết ức hiếp vương phủ con riêng trên người, lợi dụng kiếp trước trình độ, ngưng thần công, luyện thần đan, đi ngược lên trời, quật khởi mạnh mẽ, từ đây bước lên một đoạn khiếp sợ đại lục kinh thế lữ trình.', 'Harem', 'https://file.hstatic.net/200000122283/file/demon_slayer_kimetsu_no_yaiba__thanh_guom_diet_quy__853198b1021746c99324b768ff4542f8_grande.jpg', 0, 'vo than chua te');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (11, 'TA LÀ ĐẠI THẦN TIÊN', 'Đang cập nhật', 'Thần đồng tên giang mới có bảy tuổi sinh trưởng quá nhanh nên bị đoản mệnh, vì là khôi phục cuộc sống bình thường, mang theo ký ức trong cơ thể một vị đại thần tiên xông vào Tiên giới, từ đây đi tới trở thành bá chủ Tiên giới mở ra con đường truyền kỳ...', 'Harem', 'https://yanlemon.com/wp-content/uploads/2022/11/truyen-tranh-ngon-tinh-nguoc-17.jpg', 0, 'ta la dai than tien');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (12, 'TRIỆU HỒI SƯ ÁO ĐEN', 'Đang cập nhật', 'Mất đi trí nhớ của mình sau khi chuyển sinh, tấm màn trong câu chuyện của Kelvin trong thế giới mới được hé mở.', 'Harem', 'https://upload.motgame.vn/photos/motgame-vn/2022/09/hinh_1_24.jpg', 1, 'trieu hoi su ao den');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (13, 'THANH NINH CHI HẠ ', 'Đang cập nhật', 'Nữ chính của chúng ta, Ninh Đóa Đóa, sau khi chuyển trường từ thành phố S về thành phố Q ven biển, ngày đầu tiên đi học, đã phát hiện ra một anh học bá trâu nhất trường, cũng là đệ nhất soái cơ, đang trốn một góc khóc hu hu do cô lạc đường khi tới trường, họ chia tay nhau khi cảm xúc đôi bên không mấy vui vẻ cho lắm.', 'Trinh Thám', 'https://truyentranhdammyy.com/wp-content/uploads/2021/12/quan-he.jpg', 1, 'thanh ninh chi ha');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (14, 'BẠN HỌC CỦA TÔI LÀ LÍNH ĐÁNH THUÊ', 'Đang cập nhật', 'Yu Ijin - người duy nhất sống sót sau một vụ tai nạn máy bay thảm khốc khi anh còn nhỏ. Sau quãng thời gian 10 năm trở thành lính đánh thuê để hoàn thành giao kèo với người đã cứu mình, cậu đã trở về với gia đình ở quê hương. Một cuộc sống mới và cả sóng gió mới bắt đầu....', 'Trinh Thám', 'https://img.baotangtruyenvip.com/Upload02/AvatarStory/20230506/20230506213927_109.png', 0, 'ban hoc cua toi la linh danh thue');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (15, 'TÌNH YÊU TUỔI TRẺ', 'Đang cập nhật', 'Trong chín năm, tôi luôn ở vị trí đầu tiên trong suốt cuộc đời học sinh của mình. Tôi nghĩ rằng một khi tôi vào cao trung, mọi thứ sẽ vẫn như cũ. Nhưng, tên khốn nào đó đã chiếm vi trí của tôi! nhưng không, tôi không muốn từ bỏ vị trí của mình!!!!', 'Trinh Thám', 'https://mangadex.org/covers/f664ae8a-7c44-4548-a60b-5cb6f00dd142/280887f7-3724-4b18-87fa-3cc6b7fec236.png.512.jpg', 0, 'tinh yeu tuoi tre');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (16, 'HỌC VIỆN CAO THỦ', 'Đang cập nhật', 'Phách lối không phải là sai, cuồng vọng không phải tội, ta bản mạnh nhất, không cần để ý! Giáo hoa, người mẫu, ngự tỷ, ta đã thích, mang tới liền!', 'Trinh Thám', 'http://alotruyentranh.com/ImageComicBig/hoc-vien-cao-thu-17424.jpg', 0, 'hoc vien cao thu');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (17, 'SỰ TRẢ THÙ CỦA NỮ VƯƠNG', 'Trọng Sinh Chi Ngược Tra Nữ Vương', 'Động tác tiêu chuẩn sau khi trọng sinh là gì? Tay trái đảo lộn cả nhà tra nam, tay trái ôm chặt đùi bá tổng. Cô vợ nhỏ:’’Chồng, em bị cấp trên tiền mãn kinh mắng là chó, làm sao đây?’’. Bá tổng: ‘’Đã sắp xếp xong, cho nữ vương đại nhân thăng chức để cấp trên trước đây quỳ làm chó!’’. Cô vợ nhỏ:’’Chồng, tiền lương thấp quá không đủ nuôi con, phải làm sao?’’. Bá tổng: ‘’Đã sắp xếp xong, tăng lương cho nữ vương đại nhân, tăng lên 10 triệu tệ mỗi năm!’’. Bá tổng sủng vợ không có giới hạn, anh yêu em, tất cả tài sản của anh đều cho em.', 'Ngôn Tình', 'https://i1.wp.com/s2.anhvip.xyz/comics/su-tra-thu-cua-nu-vuong-1627360136.jpg', 0, 'su tra thu cua nu vuong');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (18, 'DANH MÔN CHÍ ÁI', 'Đang cập nhật', 'Một cuộc giao dịch đã bắt cô ấy phải hiến thân cho người đàn ông tôn kính nhất, đáng sợ nhất ở thành phố Đông Lăng. Ban ngày, cô ấy là một người con gái bình thường như bao cô gái khác nhưng ban đêm cô lại trở thành món đồ chơi của anh ta… Hai tay cô bị ép dưới ngực anh, khuôn mặt kinh hoảng: “Nếu anh dám làm loạn, tôi… tôi sẽ tố cáo anh.” Anh nhấc cằm cô lên, cười tà mị nói: “Cả cái thành phố Đông Lăng này là thiên hạ của tôi, em dám tố cáo tôi sao?”', 'Ngôn Tình', 'http://alotruyentranh.com/ImageComicBig/danh-mon-chi-ai-15688.jpg', 0, 'danh mon chi ai');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (19, 'KUBO WONT LET ME BE INVISIBLE', 'Đang cập nhật', 'Mọi người dường như không nhận ra được sự tồn tại của tôi nhưng mà tại sao Kubo-san lại...!!', 'Romance', 'https://cdn.myanimelist.net/images/anime/1818/132330.jpg', 1, 'kubo san');");
        db.execSQL("INSERT INTO truyen (id, tentruyen, tacgia, mota, theloai, linkanh, trangthai, key_search) VALUES (20, 'THE TEACHER CAN NOT TELL ME LOVE', 'Đang cập nhật', 'Một học sinh lười biếng và một giáo viên nghiêm túc. Thoạt nhìn, có vẻ như họ đang ở trong tình trạng tồi tệ, nhưng mọi thứ trở nên khác biệt sau giờ học ...', 'Romance', 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/1.jpg', 1, 'teacher can not tell me love');");



        // Chapter
        db.execSQL("CREATE TABLE if not exists chapter (\n" +
                "    id         INTEGER   PRIMARY KEY AUTOINCREMENT\n" +
                "                         NOT NULL,\n" +
                "    idtruyen   INTEGER   REFERENCES truyen (id) \n" +
                "                         NOT NULL,\n" +
                "    tenchapter CHAR (20) NOT NULL,\n" +
                "    ngaydang   DATE      NOT NULL\n" +
                "                         DEFAULT (date('now') ),\n" +
                "    soluotxem  INTEGER   DEFAULT (0) \n" +
                "                         NOT NULL,\n" +
                "    danhgia    FLOAT     DEFAULT (0) \n" +
                "                         NOT NULL\n" +
                ");\n");

        //insert chapter
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (1, 1, 'Chapter 1', '2023-01-31', 25, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (2, 1, 'Chapter 2', '2023-02-09', 13, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (3, 1, 'Chapter 3', '2023-02-11', 10, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (4, 2, 'Chapter 1', '2023-03-02', 5, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (5, 2, 'Chapter 2', '2023-03-03', 4, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (6, 2, 'Chapter 3', '2023-03-03', 3, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (7, 3, 'Chapter 1', '2023-03-24', 8, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (8, 3, 'Chapter 2', '2023-03-24', 9, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (9, 3, 'Chapter 3', '2023-03-25', 8, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (10, 4, 'Chapter 1', '2023-03-28', 3, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (11, 4, 'Chapter 2', '2023-03-28', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (12, 4, 'Chapter 3', '2023-04-04', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (13, 5, 'Chapter 1', '2023-02-06', 1, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (14, 5, 'Chapter 2', '2023-02-06', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (15, 5, 'Chapter 3', '2023-02-10', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (16, 6, 'Chapter 1', '2023-01-13', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (17, 6, 'Chapter 2', '2023-01-13', 1, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (18, 6, 'Chapter 3', '2023-02-13', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (19, 7, 'Chapter 1', '2023-01-21', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (20, 7, 'Chapter 2', '2023-01-21', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (21, 7, 'Chapter 3', '2023-01-21', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (22, 8, 'Chapter 1', '2023-01-19', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (23, 8, 'Chapter 2', '2023-01-19', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (24, 8, 'Chapter 3', '2023-01-19', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (25, 9, 'Chapter 1', '2023-02-12', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (26, 9, 'Chapter 2', '2023-02-12', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (27, 9, 'Chapter 3', '2023-02-12', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (28, 10, 'Chapter 1', '2023-03-30', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (29, 10, 'Chapter 2', '2023-03-30', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (30, 10, 'Chapter 3', '2023-04-01', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (31, 11, 'Chapter 1', '2023-01-21', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (32, 11, 'Chapter 2', '2023-01-28', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (33, 11, 'Chapter 3', '2023-01-28', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (34, 12, 'Chapter 1', '2023-03-03', 8, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (35, 12, 'Chapter 2', '2023-03-04', 16, 0.0);");

        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (37, 13, 'Chapter 1', '2023-03-23', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (38, 13, 'Chapter 2', '2023-03-24', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (39, 13, 'Chapter 3', '2023-03-26', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (40, 14, 'Chapter 1', '2023-01-09', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (41, 14, 'Chapter 2', '2023-01-09', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (42, 14, 'Chapter 3', '2023-01-11', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (43, 15, 'Chapter 1', '2023-03-16', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (44, 15, 'Chapter 2', '2023-03-24', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (45, 15, 'Chapter 3', '2023-03-24', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (46, 16, 'Chapter 1', '2023-03-09', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (47, 16, 'Chapter 2', '2023-03-09', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (48, 16, 'Chapter 3', '2023-03-09', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (49, 17, 'Chapter 1', '2023-01-23', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (50, 17, 'Chapter 2', '2023-01-23', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (51, 17, 'Chapter 3', '2023-01-23', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (52, 18, 'Chapter 1', '2023-02-17', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (53, 18, 'Chapter 2', '2023-02-19', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (54, 18, 'Chapter 3', '2023-02-19', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (55, 19, 'Chapter 1', '2023-04-29', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (56, 19, 'Chapter 2', '2023-04-30', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (57, 19, 'Chapter 3', '2023-05-01', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (58, 20, 'Chapter 1', '2023-05-03', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (59, 20, 'Chapter 2', '2023-05-03', 0, 0.0);");
        db.execSQL("INSERT INTO chapter (id, idtruyen, tenchapter, ngaydang, soluotxem, danhgia) VALUES (60, 20, 'Chapter 3', '2023-05-03', 0, 0.0);");

        // Nội dung Chapter
        db.execSQL("create table if not exists noidungchapter(id        INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                      NOT NULL,\n" +
                "    idchapter INTEGER REFERENCES chapter (id) \n" +
                "                      NOT NULL,\n" +
                "    linkanh   TEXT    NOT NULL)");
        //insert noidungchapter
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/02.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/03.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (3, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/04.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (4, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/05.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (5, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/06.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (6, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/07.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (7, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/08.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (8, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/09.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (9, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/010.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (10, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/011.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (11, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/012.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (12, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/013.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (13, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/014.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (14, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/015.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (15, 1, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1/016.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (16, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/02.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (17, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/03.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (18, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/04.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (19, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/05.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (20, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/06.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (21, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/08.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (22, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/09.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (23, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/010.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (24, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/011.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (25, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/012.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (26, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/013.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (27, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/014.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (28, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/015.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (29, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/016.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (30, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-1.2/017.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (31, 2, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-2.1/02.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (32, 3, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-2.1/04.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (33, 3, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-2.1/05.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (34, 3, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-2.1/06.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (35, 3, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-2.1/07.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (36, 3, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-2.1/08.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (37, 3, 'https://novel-fast.club/cdn_mangaraw/maou-ni-natta-node-dungeon-tsukutte-jingai-musume-to-honobono-suru/chapter-2.1/09.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (38, 3, 'https://i115.ntcdntempv3.com/data/images/18681/401845/012-fix.jpg?data=nht');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (39, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/01.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (40, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/03.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (41, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/05.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (42, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/06.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (43, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/07.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (44, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/08.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (45, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/09.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (46, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/010.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (47, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/011.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (48, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/012.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (49, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/013.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (50, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/014.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (51, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/015.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (52, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/016.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (53, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/017.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (54, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/018.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (55, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/019.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (56, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/020.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (57, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/021.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (58, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/022.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (59, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/023.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (60, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/024.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (61, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/025.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (62, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/026.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (63, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/027.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (64, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/028.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (65, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/029.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (66, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/030.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (67, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/031.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (68, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/032.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (69, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/033.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (70, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/034.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (71, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/035.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (72, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/036.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (73, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/037.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (74, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/038.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (75, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/039.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (76, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/040.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (77, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/041.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (78, 4, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-1/042.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (79, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/01.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (80, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/03.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (81, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/04.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (82, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/05.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (83, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/06.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (84, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/07.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (85, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/08.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (86, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/09.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (87, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/010.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (88, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/011.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (89, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/013.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (90, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/014.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (91, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/015.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (92, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/016.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (93, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/017.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (94, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/018.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (95, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/019.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (96, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/020.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (97, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/021.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (98, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/022.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (99, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/023.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (100,5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/024.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (101, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/025.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (102, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/026.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (103, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/027.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (104, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/028.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (105, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/029.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (106, 5, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-2/030.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (107, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/01.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (108, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/02.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (109, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/03.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (110, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/04.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (111, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/05.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (112, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/06.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (113, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/07.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (114, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/08.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (115, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/09.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (116, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/010.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (117, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/011.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (118, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/012.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (119, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/013.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (120, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/014.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (121, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/015.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (122, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/016.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (123, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/017.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (124, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/018.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (125, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/019.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (126, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/020.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (127, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/021.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (128, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/022.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (129, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/023.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (130, 6, 'https://novel-fast.club/cdn_mangaraw/tensei-kizoku-no-isekai-boukenroku-jichou-wo-shiranai-kamigami-no-shito/chapter-3/024.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (255, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (256, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (257, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (258, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (259, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (260, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (261, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (262, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (263, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (264, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (265, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (266, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (267, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (268, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (269, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (270, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (271, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (272, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/19.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (273, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/20.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (274, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/21.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (275, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/22.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (276, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/23.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (277, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/24.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (278, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/25.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (279, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/26.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (280, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/27.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (281, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/28.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (282, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/29.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (283, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/30.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (284, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/31.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (285, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/32.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (286, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/33.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (287, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/34.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (288, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/35.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (289, 7, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-1/36.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (290, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (291, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (292, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (293, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (294, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (295, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (296, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (297, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (298, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (299, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (300, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (301, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (302, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (303, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (304, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (305, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (306, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (307, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/19.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (308, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/20.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (309, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/21.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (310, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/22.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (311, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/23.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (312, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/24.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (313, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/25.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (314, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/26.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (315, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/27.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (316, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/28.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (317, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/29.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (318, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/30.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (319, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/31.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (320, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/32.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (321, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/33.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (322, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/34.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (323, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/35.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (324, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/36.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (325, 8, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-2/37.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (326, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (327, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (328, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (329, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (330, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (331, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (332, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (333, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (334, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (335, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (336, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (337, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (338, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (339, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (340, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (341, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (342, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (343, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/19.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (344, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/20.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (345, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/21.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (346, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/22.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (347, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/23.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (348, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/24.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (349, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/25.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (350, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/26.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (351, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/27.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (352, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/28.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (353, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/29.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (354, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/30.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (355, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/31.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (356, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/32.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (357, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/33.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (358, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/34.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (359, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/35.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (360, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/36.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (361, 9, 'https://cdn.hxmanga.com/file/majekayoo/the-eminence-in-shadow/vol-1-chapter-3/37.jpg');");

        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (362, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (363, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (364, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (365, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (366, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (367, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (368, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (369, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (370, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (371, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (372, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (373, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (374, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (375, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (376, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (377, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (378, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (379, 10, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/19.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (380, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/20.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (381, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/21.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (382, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/22.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (383, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/23.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (384, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/24.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (385, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/25.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (386, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/26.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (387, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/27.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (388, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/28.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (389, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/29.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (390, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/30.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (391, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/31.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (392, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/32.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (393, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/33.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (394, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/34.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (395, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/35.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (396, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/36.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (397, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/37.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (398, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/38.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (399, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/39.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (400, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/40.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (401, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/41.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (402, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/42.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (403, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/43.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (404, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/44.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (405, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/45.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (406, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/46.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (407, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/47.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (408, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/48.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (409, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/49.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (410, 11, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-1/50.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (411, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (412, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (413, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (414, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (415, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (416, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (417, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (418, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (419, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (420, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (421, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (422, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (423, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (424, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (425, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (426, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (427, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (428, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/19.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (429, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/20.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (430, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/21.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (431, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/22.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (432, 12, 'https://cdn.readkakegurui.com/file/mangaifenzi22/mashle-magic-and-muscles/chapter-2/23.jpg');");

        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (442, 13, 'https://1.bp.blogspot.com/T72B4vtxbGCkmiTaCmBItdWf_eWVO4rk7UC1WiLeiVTXXpNzk4xF6X3mCMVZaPub9IqJI4i75OKKlma0UhksxibxmwhoBUIzx6YgEDfQVpwzkqqbGTlCpbHLbF1g1J-_ztWBUsg4Vw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (443, 13, 'https://1.bp.blogspot.com/Je1btb2sIElWpjDuWoKntlSWS23AAX5kMzTj-xzxGTkyKV7NO0fS2B6YZYHa2_iEdYEe3D2wiRQMETJ5MX2eTWCqw8JREypDmn49liHoRRjYdcoh3v34Eh5-015sIusCkWRTu7NTVA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (444, 13, 'https://1.bp.blogspot.com/PECYESKCN2RZWdnypfZyK5pOKPR-nTSUWCEtotUd2wEYEFxMj0qtG5y9JURn3q0BVFSnejO2xhLS9ZhiScP2QoPr8VO27OlymRaB2dssjG5OFOs_9O2uqEnQZw0D4XU5QC8xv0sA0w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (445, 13, 'https://1.bp.blogspot.com/roiBcxsitbNE1x4WwaxUiSajuMPBp_LQ1lBmcl1uU1yNzgZ76CrDWkcbRLxXWKdu4V17PbQe0UcfB78dHHjfMUGW67i9DEsVQZO7tsOiSvNuFnu0KtjokuS4oZU4OP1dF6JxIbkHAg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (446, 13, 'https://1.bp.blogspot.com/DVKF9974G454ceY3SC1Bg10Mczx6xf5GsyoHaat37acYek50yE-VTbWCN_fegoaMDm4ry1pYv5qpMzMRa-0yO1vT-NrK6IvT0qwZ1DnGBkck7JsgQy-xv1dytNp1TtiAnfDaNH9avQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (447, 13, 'https://1.bp.blogspot.com/ZC0Z2BYQRWZA2XBtFVlma2Uegz1eLmreoOWZRrORQgljZzI6E_333gNRAm8SW3YH5GOv-cl1tNfgabRukLeTfTde75FqZsAJPnWG7bdJPhx9BWcY5XfLcNDTYzOHeEaezVVIqP_myw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (448, 13, 'https://1.bp.blogspot.com/87gjeDOry2SiM1uWnoZCha54nIMB53gmExMKM-ACdqQyp3IqqidBhLzVKL_k4_C7tIpa4Np80uqoHhXlF2aYo7byZ6EMjZRDmr024y4BMugaSpqLh3YVJRJYKkzHdkVuoEyD8Db4Lg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (449, 13, 'https://1.bp.blogspot.com/-NOWTCJ-dfPbPdpDKq8QKrlw3opY2OEGVmI2UeIsn_cJiMkG9j3FPCeSlGJITb5KGLFK6ubCpVnjXgtzeITnCl-BegrW6XShJNJU8pTqN9Mx-Le7tc14Q4PQsUHfnU1yUXZPZBSLjg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (450, 13, 'https://1.bp.blogspot.com/ox2tLhZkp6GSBhvj48_cWEdMyGCYIfo0uDM736JfF_JRAiWn8DnXRg7QgYLA1IT-LgQbnZYyLNhshdCodD_YKPGcpdmCecFUeF6S2DSnUaCztnOzcUhLKiUkBtK1UTMOwMzLb-c9Fg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (451, 13, 'https://1.bp.blogspot.com/LqrB5XeVXid_uGLnTmKIrxt-BLf7uOR3-4vJC3NlWnC7CmIuuAjpXOsm0d9Gdhu798zLSKtr95aSs4uOV2i3e__Bz3NYuv5JRPuWhcxBE2d2O5hJmJzUbF93ZaxMp9YRSWhBpjpPGQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (452, 13, 'https://1.bp.blogspot.com/Nx-we9PqlzyeqeD75obQIrRZ-d04oZdLYGtaCyWRgoWG6cBHuhhmBESDfKDOcEI5OnaJeYGlPOuwPkaz7-7Is4K1PDOkePEwvhYALm27CT5YNnx50xJNQEyiAsBn87ZrzF1j4eoxSg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (453, 13, 'https://1.bp.blogspot.com/a7sZhyTJCSBXaQ9qDixOMP8SV7MSMsiK5DSWC1oH8_bdpEz2hYFjWd-UIo_EHIw4dr5EaVvyzOQLFCDcB1YmnmX9KrBFlhUfToHawMRlbeajz2TIbX2toHTOlquEmjV0ZXarxuC13w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (454, 13, 'https://1.bp.blogspot.com/gI_4hQ5lKiqdnTURWjLgmAVZq2ARI6hHrxRNbim27AvGQMOXawNbSjjlVY_7E-8AHMapTEsoyw73_xKPUCTyTkqdCTi_0DBEXtNb0nE_cLF_Ldkj3v8P3UItgDXF9K57ktTPNsWDZQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (455, 13, 'https://1.bp.blogspot.com/AeEXWiIYWGor1mWKLjTvYo1X-oz3WbIirZ2HsKVCFTWEnK_PEkNe4NvTzT9XJnfs6SGXpybf4naN1rWmHbuL0BW0FYgnVsIh6C-g-XWB-Ys4jdXvpPNwRpLkftU1b6FVr-HV9jtQNA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (456, 13, 'https://1.bp.blogspot.com/PvijQyCnFlz-soUhfRSx6Dnbj-cfpj9m2VCc4jKvZbc85NQChI-VMLE77rFlorIxaABm4rIjYNMBhSdRmaMoffM91RW6nYm6KCbLax_95XBJaGZ0fQ34qT7DshuD13Rsz6vlZMO8lg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (457, 13, 'https://1.bp.blogspot.com/-rnQ50_rf9ilj4Um-e8ZHundS8OdxwAvv6cJqbPNU0CgstBEWacUZ0XpO2sh6mn8ZAYuCacUZVdPq_LtaS4OWARVy4cP6Ltt3kwfwjL1sNfolglXsZsPJYqNGWg0KHjUpoE1oA2iPA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (458, 13, 'https://1.bp.blogspot.com/cNjcWW-cS9dqNrj0L6eIQAMIPwAgMkJp3H8jgAi_9HK-dYHlT7k-8SVALd9pa5qg-G3fERBuGlfW5ALZnqAVpB2tSYiauZnmzDgJFFumI7aueh5pzVqLr2lkeIAA4EjFkfEoA_SK1A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (459, 13, 'https://1.bp.blogspot.com/r8GboRJm1TXJPplRF-vEuvKRZWbhSj8G9L4xNkbaiHCejkOh-0o_1RP-XDOwCIeCMYqYGkhnWm22Qt7bUmHXBcvNzvH58NnSP-Z_h38Nbeh0WjMhHMX01bvpB20bMl8SVcVnfi9hpA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (460, 14, 'https://1.bp.blogspot.com/3BDUJ_KbG33dHNMGLdFlZJWuxNZc73GCQzQsI9mtjbbsRX8IhvoVKBIfIIqYIrNaqoL-BbyMSjY002h22rnWkdJyrleA1N9kw21p0FUEdrlEq698JBL4nP1m4MlSAWwK7_C41n7Tvw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (461, 14, 'https://1.bp.blogspot.com/0u8635SHKyaOzPrUcxb0EyUMR7dmpU99ZcS1GgzMZnwKqf95__ldNGaP9hiOVO49UXtUuV3ySeZMS2qbrx7bzzvOvoHlA4MhZtDXSax99EvA5_Qv2GO8iVHHn6QudOt2yKEytMrpow=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (462, 14, 'https://1.bp.blogspot.com/B4TwQyp8ImcQ6eNZcmM6BFD46o72ZfyOkudPshMVayVs_rpNMUYEbJCj-C6vxUUL0hIRGfAV8syptIRZUjTvB8BZad2It_dnMpDrCgznUEhSJiEbzau9cKOvzqIic1vJqjCq8feINw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (463, 14, 'https://1.bp.blogspot.com/2KpYM54qnQdpG8YWc7Nf-tFWXn5S7dSpXirdkOIStyQKt6NrmAGq14yTAFof28Khv2NokSAzlC3mpqdoStN_p8zasdAVB5KUfBzZHNP3Eff8QVFW0x6fDM_hVLOem6qymYva7kiYHQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (464, 14, 'https://1.bp.blogspot.com/f0DobPVCwZZWXJNtTcCWEsGBoZubFC4L9dRmkLN1cv0h8NYWqBl0li9sa-Vv3QHka1n3LrW4IU9Wtss-wE5Vd61K515OCtCkF4FoOVzAz_HLxVqtfkrBF89u6rd8Aj85NbUsUdsJRA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (465, 14, 'https://1.bp.blogspot.com/TqQuUzRnuYSG8aoAwMsjpCXdx1mSwK-7Bhdw8Dg0lBBgq9QoquEvI4LCNXRif6vEkcXW0dERaYg--q3WHlzEJ4wgLwW0s9Y3Zc2bffeQCHUaeWhA6RJfDdIJlU-u4qaWhhE40ApbFw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (466, 14, 'https://1.bp.blogspot.com/921jHySPtPPUu8skkoHueq7hMP3XWWCpjO0nFQAGYQuR_FkVUOIHWlmyu90IPYzksdsmmaWI2HSwMFkM3E2dTQd1EEOFYn26uCiCIQGVRzXJUNTHW-DSIrs2WxNr4STNZYClD6GBog=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (467, 14, 'https://1.bp.blogspot.com/l9YvwvRWH5hgWs3FJFkcV683SUyxHs38jA7Jfjo6oRX3UsKZ2IqKU7Mt8oQmA5F4Y_ZfnyolOByZ4LIMI_wW_5yfjMh3cx9BpYxrdKPxAXbW0Skep5Tt9Z2l5QVs3rlSHV8kP6Bsfg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (468, 14, 'https://1.bp.blogspot.com/RS3KV9SxrR5n51bcnHt5xCx-ovJZ6d8rAyyAnszF7T-yp2e2wmsElnFzzdEIOAqiRVGwyPQkKNqN2doubGnHrJ_Cw0AiPymnNTlxBI2b9mbNcj7As0yU9ZmIY9039-2uddrFzeoTKA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (469, 14, 'https://1.bp.blogspot.com/KEgjNFD5meJ4jebw-QJ0dIweY8YGGRM9JRrOgCVx0A3Kk8PNcY-WQExNEFTEcE1Wt8djoUr7BdJmPFEyPYD71gr30aoDHHC4WGta8SO3DeHaSQS8uoAsNYuo8lzh2fIoVeTjhwsOdw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (470, 14, 'https://1.bp.blogspot.com/R18JIoocW05ZBQ79qlrZiozYFo4_3iGV2BuRETlu3ALwRBcDqjaIxw9qDvvouE4fxSmJqIcRXzWOOIXXEHLOO5smGnUuv69kPAe_v7MDOX0qJlnQlA-XOy0uSy7Kz0mg_PwqgDlAgQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (471, 14, 'https://1.bp.blogspot.com/6Iy8RKJwKdMME1R6wWLmHnbyv-CaT9TIL3LSvD1hiYrRw-5T3lMJz6TaMMMHU832YGiQC2yag5Ta60QQcGpmlL0tfGKVNZEO7looZbcMSWclu615n8J7SEwLaGvlQQj5lOyln1UBwQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (472, 14, 'https://1.bp.blogspot.com/bf2A_ApOOgVb20g3qptToZW7K0QIBIjhVD9KOa7U6963H-lC0ncS7HdwcJx9qqTnhQPdho2vPnZBfVN7h_lrqFntesV5dqn5JIjDFKepgNwWv0sEuSWh4cu05P_6KbjggnrjcOquzw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (473, 14, 'https://1.bp.blogspot.com/BXfL-aLsQb7hHZWdmaJGRkTBViJyo_d8q-O7KdLCWJmyOhSIvl9GbBjCdPTrUaKGmnu2AEl2g26YYqbguZAYqLtJ645efP3s9K-sVClNKDDWT5eP7AhLwCU_7reoGsZw_ySsaCAj5w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (474, 14, 'https://1.bp.blogspot.com/4bkeiPJm5ZbSScTI2WMAcyr43OFCdUy52bgtuQ579DWuBEPqqsFMOFbtASooMwSUpEaUArER6qZXRcA1Z4Y53rmUKT2ZXxlGiBsYO_WbR0Mwtfy18k4poqkYYys2eawBLjEAvA6BMA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (475, 14, 'https://1.bp.blogspot.com/6giSSYTYb4iwpN-CZqoLA2y6Y057aPoljoQ5GXmiiGOsUNM7Hj5kSsuBLXzrDfw-i_wc88fz_QVoBUei3sefa2JIreC5GqSWvugoybJQkxfF24oByKwU5q9wl-lJMRTaSO3Rt85ZMg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (476, 14, 'https://1.bp.blogspot.com/lzE-VrZCW3nF7E7XBeDRjMf5pgOdlABp14Br9viLosis7W6kbfrZ7NoJ0inJ47l5Sc1JYou5BAXBeNvE3Fs662oVQ_DJboVrTa4gXstKkT5QgMryJFRGyKcPTrSIejP3mxFte4-L9w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (477, 15, 'https://1.bp.blogspot.com/JstkMmCVcgz7jMvL8yPq6SaU30XU1kJlCZoKB05rVLdOfjYm_Jpgcq8fvB3hSxkVlvgNS7xVjxklEoj5I33eV8gESEyNJrkUWD0L1-YCvYtBHivouzAZ7vUVViw2U_8Oycjj-oVf1Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (478, 15, 'https://1.bp.blogspot.com/7EnD8fzhNe5I8bsZ53rGuHjXPMwKfn45pRpf1VFSS1RtFOJFFnRlx1TtO4CeMtFyupAXafND89AjOYwlopKIQzihrwrnLzxpksUrgt7Sj55xzI5rXlomq0q03pfX_u1D7UFFsMVknw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (479, 15, 'https://1.bp.blogspot.com/BHBn2KxSDqkD7BkQc-7i-Gs92YspGc0KqQswUYJxtyrNRObdTO1uBSEAUBkjtTjXT3-GXNSua7tJJocEeHNIU6EoSI2XzLcfxk9hU0TWNSpVrsaX95SMZuaSsxVKl43NuE27N8ht2Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (480, 15, 'https://1.bp.blogspot.com/qXjkLMtM0TLnTYJRd3VZKHzvw6GUHjfQ5yaSNXx96zaLrocA12f5dhQ1d0BhpcNGqXcaUguqnGMqkp2Trxow-X-ZKAyfPTPBl6Oljk7V-w6xAYyanqNgKXc2aFjZ0QDtXKlwGkqOtQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (481, 15, 'https://1.bp.blogspot.com/ItUNv4ZbG7pDYWHrTdcyqW7wxTzL6t3jSeFrWhWnIw4-MQLVuekbrPvc619KhzgubYt55bsOTrTR2CbaFpRtxfM85MkyMSxN3xlbyrBse37LTpLyM63rxNXAGN86Fvu3D3lSoFoKHQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (482, 15, 'https://1.bp.blogspot.com/5Pq_SAEDofShpmJyGiVJ7kOesLzhhpjl2lm07a_sh_P6QsS3JJtnz5e1qDBa2KOGqjHv66BB02GiILR1XV39eWjYvjt3g0MbH-NrCm-naAZShgNzh5KQJOssA7-QEGSAcdwcnNrqAw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (483, 15, 'https://1.bp.blogspot.com/w3LNZr5_aFK0F_toUqH5E-wEuA58yGCKCRJRQwscTOjiya2ycC-B5cOb3izNnXBjRQM8AoibdsLpokO4-bcekTZ84FqR0n839wgeB6a6KabkRUp8xilIzqWJj38n8qyiUJsuYbuFVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (484, 15, 'https://1.bp.blogspot.com/c0gBafoC9OjPc2LdUzl4BaZ3iCO1a5UUY8TV5Ghn5g0KUM4bj2-DC3MSp0UwbllAf26OnYZi8ZBqDCBAhVx2Hu2RogzqfXjH6bHvdfo_KJbt5EdHoIyNrzBlxdn8yZJxcQDihMozVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (485, 15, 'https://1.bp.blogspot.com/-CW2EZ304zBcJfuDmod4LuDSy3jcsBDQOsR3zv3O957QHpltEvSD4YdVoMK6tdcpqcL-rsk9Uv590eKRn1PxlHl3Tz8dxNcHshzCB0KsrjMCiyvSaLAY41RGSzuSijMi5elz1iyB7w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (486, 15, 'https://1.bp.blogspot.com/zU4P95Codkg0RiFQihiN3cCgqOus-Hgi-ztefkaz6Ymk2zY82SdJYX-QR5-cFoFDVjUC4o6GhxfI4RDEtPLIm74ReHEsqP-4Kq3Oa4M4_zT-0iaFMumhPUXQCaxsqAwtlbSLEnTi6w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (487, 15, 'https://1.bp.blogspot.com/s6Bu_1ANn1hPZIIDTI-sJq8yuoZ0__JjE5eJM8rqWpYd48d3FHF36ENacvNIf2Isz_wQsXajqFLtRTAfNcHbP1MWq9vPvYu2HrUsMGlwUu6y2hMfU9PCgTLhRYGJa93seuo2NJu6EQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (488, 15, 'https://1.bp.blogspot.com/XcMni6hhQ2P8RjWSWLxpDX4BoRKrfr44Q4DFF8xQddXlf8R68qLzRrtc9mN6Wpnd3Jcs8mc70CN3sFSSl30d1lqMZpZRLrRNE91_cmyUyQp7zblknhjF6SpyEFUqAvGCzsN7xhGC8Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (489, 15, 'https://1.bp.blogspot.com/t9whgayy7_9ucwEOVFctG8ATxyOQMzY5S77jvz92GdkPIgtmT07zDFA_E65zsGwtxYDkqtB0NfYfFYUuklGaSdnqD1SQNp_7iKcZoS6EhMk5rCQaSeycydZ8nkfe0MVgyF7jW9qLKQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (490, 15, 'https://1.bp.blogspot.com/yMYRhKhVG0rCsxkr4W4rnkqNEey3XNsVxOlam-TDWRGPix4z3Yuq68eQWURsfOUeEwsrycrnDRrgroESUxMGoD_5kKQNKBA178-Nkp_TlAg6ScnZdb1UD4yt7YJco9cUt6Scz_VWTQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (491, 15, 'https://1.bp.blogspot.com/iT7cARcCqOQDRV-8_veJCj8n16i8LTOooFok7XQ4Sg5E7jv5GW7DcfV2gSTipS9EnisPaMBjqPdDs02zHTaRL6nCh8EITiFQctk9U0prUc96ZFg0je0aci6LtJIPxVFDqexIUaHf3g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (492, 15, 'https://1.bp.blogspot.com/NvND3LzjKmuIl11Sk9wmQf_qcpKtLgU3NIjri392JaZ3q6vzmPxFSPsm4jkCDB_qoAn7Sgy88u5rUD2Qxl84jruQ7-06PZdSCnmge-gDSqDMXNW-SQlZ5s7-JBqgw7bY23PrY5UCQg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (493, 15, 'https://1.bp.blogspot.com/CPbgTnTHnlcNXDs89WcwcOeG0bXlu2OrMj2jeXmusIorqlMjckm-hoGUmhrQ9NgQ-fP5_QyH7qjrgjgl_ZoibHk7Mfgnbm9uu-XBhHogwVYH_rtDK2gmJq6nxS6uSR5k0WKCHoqayQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (494, 15, 'https://1.bp.blogspot.com/AS3g1DLVILsl_sphlckBW-73BZu4YPZMFS0g8RA2JU87mFh3RwFZVQPhuuk9xktkLW5g70Iw_7rRgFmFjRdF6C7i9Vu9ogjm3IwliZgcCH6iabFHR3OeS_4pWMyH4sYSBUetxqGGUQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (495, 15, 'https://1.bp.blogspot.com/-T3CrWqoYHdRVtS6WhuWPCTNKvVJ14A4AUMmzCUuCaKRmhmXwxXjYOfk1nu_30UdxPVDH2qPRxK0Rm42GgmrNvYN2-kAwZtZomZXBXp6zXC2auih8LJTTT-l__ZQQRgpWIO6iDkFQQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (496, 16, 'https://1.bp.blogspot.com/QilGN0oujvHIQP4Ajhk4rSIbDZ4-8TnQah2lPCQL6LKmmxg_FPyU7Z7_ca7TQpzEH4-uEB1m76mASeXQ_0m91mibFFX9KemqaeQoUzFyCsCjsAJQ7GW3diSqYM5VBWUqh_TuCBvMIoE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (497, 16, 'https://1.bp.blogspot.com/OqfRf01-BTxBz-JnxYZDQDbbO7gQlBOcp2yRtpuEEXh0sWZaby9dDlId9777aulD_LK3w8ZYjR_oqr3ZA1JlOgpKXdN3RY-K1sOoOo6dsNs96hakQmI7kSLOvp0Jxg8ts6Yp8_qmR6U=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (498, 16, 'https://1.bp.blogspot.com/_f2YpEfi-O0Ui8H_l9ABPtysEnSrskhWKytfKIYiEmxJ_8WqV47AqPlgTuk4PzxAtpUhw1eTVzWN4AF6h2yVLVlhXBKCJIR6Heqi2w8pz3zIVHCAuEBuUKTESBuDKBzSM6Jucq1km1A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (499, 16, 'https://1.bp.blogspot.com/TVcr2S_Mq2f3-iYfrOudaBYi3pBVMgBdHFEe3ZefqUFcJpNQ9RQKgl-jz3tWuAIEeZsAGSbrAeUxQMDlU7phzVCeIthVOfUWAH5RsMtZ_eUY8O9E56Sz-fb4tU_wgUaAl2FLZ6WxJgE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (500, 16, 'https://1.bp.blogspot.com/iMUk57u1wOf9M3EfNsdtQmoa7ZaeVr9bGgNxFq65eb5-J955pImqRhgqpoKnnSjzmGLQl1oowVFXAGdMnagRxUdXd7vrMPpbAE_oY1MF-qJvuWQZAI-0_QhJWdmg43SKZryDKMTrY_Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (501, 16, 'https://1.bp.blogspot.com/WVAO-4HVkEtv8sxpBnEnAUqYFJ5RlqDnquYDyyx4AaZbmxC5KJK1aeZtrggkJoDNUCBbWe4sHHbeEx8n1gYcrea1Q6lILatno3VBRwGa6IrMEvrR1lKxDHuFqizMw1hziHjCwkgmuEw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (502, 16, 'https://1.bp.blogspot.com/sNL1v-Z9k50NVSLlA978LdH1rZD-tOKpFZhQ_zefm0ITLmRYjay_L09tYYOVvYSqw6nV0bY_7pWdJmSz9gk7YGz-q2DLS_qQy-XyJkMlGo9nC9oTtE2F5MxbmwB5yrZdAMyXVmPsodc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (503, 16, 'https://1.bp.blogspot.com/fXI8X-A9VmwTxLhA_SNgYmNCIxNysI6wu3hTZ_dyTX_dnTrWqlVQI4-GrssiTghWyXLLS7QHQL0UZyY4T-FySc-1H3J_cNVKkXSCNDtFwpauObTYb7MphafrQs0KxPs0Z4GTY3tQcQk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (504, 17, 'https://1.bp.blogspot.com/k2AnPjtFiF8CRTPuC3Wd5A5BNgclImAUd0llO9A9qgnafqrDPBjjsMoEk4_uQcmEx0kEO34W8cqMqjXKBCsV3OcTAkxD3IgmMKMdhtrxWythSiIQ9tyzHAeDuk1m0D6dtS5ECptw3bs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (505, 17, 'https://1.bp.blogspot.com/uePxR6vKhnMvGKi8ZOt81hK0AEJiPKV0r-P92hxaDkl-Yu1IRUPe3TZp6h39f6qAvmsxFS2zyWJII0d-LT_RHAzou_EhSgHd3xSaeQF6BRbTBk0rgK9SIdwm7Sn795wtmF3NrwMPFBs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (506, 17, 'https://1.bp.blogspot.com/_uNa3MamRAtFprnFfb26BR5Yt14chwnwtiwQlwtNbKfqicsaIhn8-GsIhlZ8P3Gjt-52kD3Qjkon4S1K_kUiX2qEpsZ3SZYhyWAADyDywYA__k-WiOVKqvuHS9iZkRZTVVICvAXhGys=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (507, 17, 'https://1.bp.blogspot.com/8lJILMaPF_frCtMjLxFU8w52qLUl83IhyFb_VlG8W3NHQw8olG6N1AEPutQXIyLGSn4rMnGczLvdI71CgWT6o9G6FYb_TN_fHmOu6aGr6IEKD4KaKW-mKH-wOtA_ZtrSttOYcuqa1ng=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (508, 17, 'https://1.bp.blogspot.com/6Cqf-QVFMCNwEbFqBGY6BhSpIAoCX-cfh3KIZrhoAncJbTx0mLez5W_xBLBVovXzE-7lyDJ09HcigjUWgFSKZnU9MEGCOCDfDQls_djA2TxrcOWN2i_nVEoRe1iXXPv-rqUAau-1Suk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (509, 17, 'https://1.bp.blogspot.com/J7oVFYp3tWfaMdRtmuhjxZN2tqGJwHWzgcwpPftB9Hu8noq54-HA5wXlMrj8QFMxyArCbLtb_oULMOfPZSfJ69b4baC0WgGFa5jSI7hmxerWBSzq0yci7tc3R5uLdNDPWxMlHZwXflQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (510, 17, 'https://1.bp.blogspot.com/6WFnAEtJO8CHs851XoTkXa1-DG7P0G8E4FfLNBQVtFgeg8J6IEa4UTIYqci65XOdETYtOO4VLGkTupOiUgskiidgOVkZ0YpFCLOnEgJDuBkYK8uZvKxk-dRGHdxjC30Rj7_wRquMdLM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (511, 17, 'https://1.bp.blogspot.com/02v8pYqF_vA79uFV6r5XFgFTcQkktDGiALJsnjMBp4z50SRkgbsE6CfKZlQ_JgNxWjrS4jd0VIJtobdRgPYzHshm0pTT9QJD70CDqnIPRG828TUrkYiEgGB3Q7_nk2J6jrvdmvzJZ9A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (512, 17, 'https://1.bp.blogspot.com/QvD2ogURcdZRHYhvQYy5AOaKBXNGP0mNjaa1D_R5QRx9Rid1IMFeW1tix91BUjw3GWnuXhqKvwR2JEZTXjA00EYaXGQUsVbVqUSwBGAua-_VnNVAwJjhWLBMghKOpDZFb8lqSlThP84=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (513, 17, 'https://1.bp.blogspot.com/m6EcTZoW-v4E3whxJzioc0q_ix5_51omxznGsqgmMKMImOz2mkpnYPRyu17dkP0DG0ZVmDJHW6bG_dGmQXTWlwHgly0dy2kVNBWC0ZgFiYsrjzqU6Lq1qJg9566eCezqbjdQrTGqv7o=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (514, 17, 'https://1.bp.blogspot.com/sHCDwHamwUQkBaWPM0gJomcJJYkjl3YGf090j1atag-qihP17XRPKibpOyCTi5jJorhNcVadz-q5Rs8YDrx8KpNrvFkb9VrV-cyV4hMx8utbi4zfnHrJqkcqcZ-tSWfi4q_FG6iSAqI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (515, 17, 'https://1.bp.blogspot.com/yCTp3OAxxu-eWXMHISSJ7BjP5Tnp2spRnuMCND1qkwH07c6Pp6nXA2bzdjsjirAqzxhBdB_odHJe-SDYL0ag5FEQ7l6OG5hwITXnTN0o9qL3Kj9CpA0GyU79ZHOFlW0h2C0OokWwSsg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (516, 17, 'https://1.bp.blogspot.com/_NoM86pYlKgJuMm5vOL9fILMsEJkmKsNUVIAIg2HSkDG2TdI6fQufIyY2AqZUIGrRHzwzx8AHbhw_nn56r2cf0ViwR8IUqhfnzNSGptvI2XjTQKF2J40KcaPVRrQkSlqepSOdV6MdY4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (517, 17, 'https://1.bp.blogspot.com/uYbg79Bv06VKrNwWzly82NnSDXMcblD6v-elILaqnVjzZgzr9BYxqfzqBIgyNN6c40GlPg4RyCcKV7oMBHm7kSXCZv-TIiIM16aNaq4Sssx_BTq-6ork2EmNHbchsfWkG_LkAWrKODc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (518, 17, 'https://1.bp.blogspot.com/2Yj65JetaJ1MXdv_rKqlGty5LQHqghUbHRWvnPuGa1YT_7ZiGIs8qIxeSGgAh5clwy_b1oF9VvjulNVa8w-iGakl7aEWUgSlnIC6Qub3INI7U1k2WmgKqz_OQXW-OTss6xhuBBR3v_M=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (519, 17, 'https://1.bp.blogspot.com/42LcBcspWP6-zClRcTq-226qVp95EQZOyxIGw-U4ACLQFvhBnKDGrl_rQGMj41VylolhkKZgPvhXqSDj31d-enOu21t5IDl_qdMZuCmsCUvpD1tLvjxopl6G36XSK-HKQMX2y4bfdyA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (520, 17, 'https://1.bp.blogspot.com/-3foHLTsPIRT39AdDKPv_ZWOheeqQ-4IuTUAyZ2R4SbfYvSX2xDytBi8fi4AL8SoL-IACC6uqs4xO4QeaqM7iI3z-YRKDh2ofVkoBUBphj_Y3j0UfedOrqz6xcA-vRZUMjMZCQmTX_s=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (521, 17, 'https://1.bp.blogspot.com/w7m_NdMKklQTKtt7p8bCq8Aj8BS7v7PPITrwMiKK-hFP-Lyf60Vll3j_cvgNnbediHTILpaiCwzLT1NNt7DcYboPWXGs2bMYw58eyMh93byDSC3qpyCwkp0WZhsqJHd3a-yvbBbfDzQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (522, 17, 'https://1.bp.blogspot.com/XoR4C1ALpBfzDcP3ziNadDmRZLlIam25WqYDoe2OGWctColm-2Ndj2sQ65QvgR2dj4iOvIoKG5ehR-CyaHIv4CUshcV5oyyxgR7DLACGS2wFoRbDlh9z8J3N1wChoaTFQ0vSfnX1nu4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (523, 17, 'https://1.bp.blogspot.com/cGBPXZw4_2vaxrZ_xNUbI8G6XzZaiWnhZR2RWumUFHiRH91inKjTQjCrIzC0fuEWEEG6_bKMpHDXW-BsNvXszYgVMT88H222_2t7mkMpCIt9psSDuy7AwyuFae0PbPIMbdGIXrcINfA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (524, 17, 'https://1.bp.blogspot.com/7KokPtqUnvDx3c8bzPbHPLJbXtJ_Dj6-ZBjLKZTSdnAmvGw0R6KAp_beUPYZ8xwm-Qc8MWu16ME_CJ2l3FYppJet7qxx6_PKbzQrXFjPcmWrqtG_bB6OMOPsTmq_eSGjICiddhO2xio=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (525, 17, 'https://1.bp.blogspot.com/64a53IDwpmsGbzkNjQjoQmw-RZrQpc5qgmecZskn2w9WcO8QC9fuz9We8ZrBR4xQ3hwXp9WvpxGpV5XN6TrpGzNzz7VFIJtmZZb2NnY-js18oSD-pAh6-uHaOw8t8n-09twbFFQRKj8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (526, 17, 'https://1.bp.blogspot.com/1eCo2g2cuOMARNmxCK2QiqCY75e_w7IUKxkS1ES4D8CvhJuYzfDtM-X4b-hqrA0FptcNhAYcJlmmjO7UJHtu7JaF2-xPQO6OGtRx70NbyM2dl1591gKTmqX5lE-SBtw0eMjtDRsBKxA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (527, 17, 'https://1.bp.blogspot.com/bf0kkBEgWf4ggI4GBrGb7WfTA94GodhjVn7TQvvYi4zkffMQOjMPtgFl87_Lm0wp8ID2nMdIPbeZp5iXNoLrqyw20X-FwvGO3zN3sbzHUAzXmO8CRguFLFquTHIZBrKSOKgQVyCrHQg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (528, 17, 'https://1.bp.blogspot.com/nTtLjOu951aPxwU8Skf3aKLBUIbGoDGLTHYQx5T7SXXWv-gECNEAtbYqqtuElUTmiJt2orJeERCNeF-hZp34jy5gGsE2iwvOrRHzH4O9oGeKhcWjLXrpGoa5ug3LouQPfStYYi7F6Go=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (529, 17, 'https://1.bp.blogspot.com/dO7b0mxONepWsuLub73hxwh1YDxF8hkuJphKU_X8QTro9xdsHwSHfAkazpqWEmZK72NHp10SxRO8CqtX6oPrRs7Y-odoGALSCXF0eE2WdrHdyMHKRCyvZ_JngTvdYc7vYuBas--eagA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (530, 17, 'https://1.bp.blogspot.com/WLgkMz9h5sIVyeG6xOFPL4tgHXva6uiwjJ7QnQ6Ooq2xgAOdIgyz_Ei1L4vKYGac5GvzSgsc-68mCy-wa9BzQUyysZjkUPAfYeWfoFBojuNgNqYgR_z4xQib_5sMqGbBHmHJ6-mh3NU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (531, 17, 'https://1.bp.blogspot.com/d53-Ujv2xD_-_aQzjZsYSFr0UcqfK0Fy2dqLT8RhAnnnCCrVDgaPMP-tcJQXQ-RsXieWsVg0OVDtqR-fw3Mg250vbrDHHAnIq2aI1E6_Cx0prjLwSz1e1mKBlLTjtpfeTUrP5rs94Ms=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (532, 17, 'https://1.bp.blogspot.com/qnUZcZPyHWzrh59HYL6BlO6FE5xpTlrAfoZ43PcD2lpTlr-c8MFLT4hrYvy_OHNhPNtUG5-wo121Q2UxCv87PDMT1g5yAIy7fXlFEndMxaPHqoyGvoq9DPqdnW0CSQMzMuY-7GyLNvc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (533, 17, 'https://1.bp.blogspot.com/ji9JSsximL_ydovBaPEaPmfq2mEV7FI5zWQSYgN3U-MPR7Ja-4utxZ5xTja3dDxtSWFrRkWPw5HabD9iMg-xT9G8bee-god1cm7eVFDbCRfB_ym5PQkvGvII6cfdlNbGZrA1clR4yPA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (534, 17, 'https://1.bp.blogspot.com/f5NeNV3_OW7bd8Kt8YWG5aQVoYFHltHa2ajyZTn6PTvwR0-slin4D-qCH72It7NXyxF6eWHu9MXGfQCEKV3HWKciQfYx3B07cu0LriuBtHCRi7TMzPcL5HQHGWxoqDgN-TYtjtlyVWo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (535, 18, 'https://1.bp.blogspot.com/b_hVyASn9Ay-IQVZNcQsrsnOXAxP3_KiKUQt06e33NFuP4rAf1KvCBb-TMWpK2ETL3eCXSEfNZDWl2cdU-I3NtDkwqSvtqCzg5_FQFAGve4Orj2ltZnEigQIfSebHlshDCXwDLrii5I=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (536, 18, 'https://1.bp.blogspot.com/zcDIdsWix7ZCxqMxgYbp-89hpVHjAcO5iVZBqWC9k9amboETg5MulJGUQhi7BOf1mr8L9fJ6nXVk8PjEj9AWNoE9UqphGPVDfBZ5DzCBk4yfP0qEkyF2A9fmc4R2xf6LWCRo2Tsm03M=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (537, 18, 'https://1.bp.blogspot.com/jNL-rN0bYXaGx1sXhmipjBUW_g8whU7SwL-EzdRSVmQ4hoX1GSavAYwlL35UjMmmPA0TuJtnPqDoL4TC-PCF_m_zIsdBuaVRkro2qN0mXS5eCM4iqfsnqwwHkN41gDfH4U-gW1Vkvac=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (538, 18, 'https://1.bp.blogspot.com/F1FEGMk1sa18U1etQn-OjXBIjuS-mxZCfKtWr15hkH-YRC7dG5qw463CWPj-h_Aq3XNtS-ZI8wlIwZm29Bhsj_LjsY-P75V1jvXw2DXBn37app8hUlHzNzb3YgvJqD1SUgNNxzaa8aU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (539, 18, 'https://1.bp.blogspot.com/fqqyHb27eO9SzphGykPBSqbh17WFwDSvjpyuOO5zCSqpOeA9f8lvdrAcgmlbXFTY8YHX9iboU4jq1pLmGXZ8d6h--ZeAzUb4xAbKZ_D_HxsRD0Pl5dqyDrt-FcUgJAHlzewHbMLjmiE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (540, 18, 'https://1.bp.blogspot.com/gtA67q_mXIWPNeHI70rP8fnVmzUlMO4vdkQMFDvp1YYre6NnIhhMTkt_Xp-MaBUsgFkRWRS4BDOWu7GskdIbKeThl3Bpc7A_opEKUZWLWDB1G_rUbXimCy2NmQGp2I0vKPXQbI3XA2A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (541, 18, 'https://1.bp.blogspot.com/5pudASAKxnbUi9-Ti_eQCD1t0kDBORvVso2NO2GPSA3dtxy0e5keBIvmV7DjSlAormh6MrKkRltIOOeMN0-cAg7xltNOGOnVJcLmGp9tV24zXkSmY0cqA5cV6vO8hnpHkORn9zc5uco=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (542, 18, 'https://1.bp.blogspot.com/HqOgWdcdY-ZtErafmyWl_xfonHPzE45XL3SzTtEBa_1G0qONq_dBmrJlnia0Qxx3EMqMSLD6pVhlZ3RqYhGNngPEe_dw_E7x8T1AgFF2_mmtn_SFx4UBTLiW34ecEpQ1SNw7Z9Zv4KM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (543, 18, 'https://1.bp.blogspot.com/WFZsmKegGL5dj26TCicueR-N_h0ox53qEHU_oT-EIMFzFQyfdGeiVe8vNbj0NOggz_RmSlQz0LCHlXdZBOnvQIBJXbnwjrVTGGluaxYVv2y0j6HZkBQzmMywJ211-AOuy9MLH-mlcZw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (544, 18, 'https://1.bp.blogspot.com/29sjSN9gX0S2iUuUiin1dSrI4wyFO412LX-ov_iX1imzGJbF7__6-oA2qtsR1sMnibAedQ8mLZ3XnKd3rL_jXjJ50oPtHtbTAIH2mDb1SDNvu0nBO9IvJVfdbCidKoyybrKlUTULPl4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (545, 18, 'https://1.bp.blogspot.com/zHHLD4QX35klDtMk8abVvCcxgrhkGMaFFO3WarT8Rkoo22K5SJrHlJ4AVtDD4RfIUCdvGVc84o_EoRqTUaYSHLM8eDnop_GYLo9F-Ct2rONhdikf9JUXsKR811fNdlhOgxtOMc1VHj4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (546, 18, 'https://1.bp.blogspot.com/kopv38o6ArvAml0Q36FXZBu539rJDEFTpUanAR9REzHPtvsdTwFUMFHZhyT_in2amkSRBq1lhqI4sqckQW9frxG6h9ffvtjjQJqSt86vic14X22w4bq3GZY_KBQlPr_jLBcW0qEUjkA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (547, 18, 'https://1.bp.blogspot.com/9Qxwu5q42icKQWf0XtqRxD6Lxlk-WINoftxTxWuqCMoglyCpmJLGMKjUgPkJOM0hjAqRqq1NrBxJ7kTylIS5Ofb0qV9HRnfHjykmSeMF1n_kU8NHMCcDA393xwXou8Ul6ME3aU0q0hA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (548, 18, 'https://1.bp.blogspot.com/TycV5JKh2pPzf8XVxPhUi2gzyaDNY1JpvGRoNBRj_h5dbafT0-g9dkxYsarSnSvVNBqjhA7CH8ATiz7WYR2DJmCMyneTdlFFZJNcw3E-UnSNS4J-PPeittw1l9MYzUeFRtETHZT63iQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (549, 18, 'https://1.bp.blogspot.com/s0Psi1pqH761l8y_6wb6dhLj9xUpAhK8N92nBv6lS5pI5Yu2Kkto3TFKLfzPD4vXm-57qGwWPmyL2ywSTX3ZGDSJ7EY0aDsqZdgxYLsVoH8UpGOnyFNUkdvUWsDftpL_IO66-kfjsYg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (550, 18, 'https://1.bp.blogspot.com/2ByGFvAFFJsMKDNBgUmm2vOWOzM-221xROaOOKmmIHdDaIQdRGKueoi8f-9SQdjCvO3_zO_bkYdTc3Tgkpi_w6OtlbyWKml4MpCuWQl61nXo8aOHapPe2V-P6Sgx-ynY3hdOIpGC7vQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (551, 18, 'https://1.bp.blogspot.com/qu7m9ivsSj8WQmRAuAUJAGrAHFgyynQJ6C_6wFr9rY-VHOtBQLbs9WaZa-7ZardYmhagw_aGnXudw5bCTg8jO45LF4u4uEhOyN8_Ha1aORw0Q_b9-Pvj7YV3A0ehk0DTemE7twFYSF8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (552, 18, 'https://1.bp.blogspot.com/W-0QSlGOhuQxaRFSYyiWHaKSh_2s-WZDIldgPJ_Bf8902DHywMvjn00uUsssudqssqXnrPNmalsKiVA_IqyCszkK0G9gDfkbGu0qRyVx4TRjPkCZNmqgnUbX3ih5hGb-U5S-UgoM4hI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (553, 18, 'https://1.bp.blogspot.com/HSVnKnBwKq3sqewYups__34eRu21k8mLznR-h4KYHXsLBsAYDSYqPX27zxPTrez9OUb24RGkNvVVSQic1ybk-S43BCR-FdchRtftwfa2yNFpp8SoBAGNWj45jupD0rnwPmCqXQoAlVE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (554, 18, 'https://1.bp.blogspot.com/Rglpqt6eos3aAHVx-PaE2IouM7jIGp9Trp6XZK3M8iiNgIy4YEfFpIw_On7bsNOX0DvqfcmBunHenaCkESMMahCW479UyeZRRB1tEOXEcCNEtNMTyxTuHVD94tVPFxm5zjGymuRTAYs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (555, 18, 'https://1.bp.blogspot.com/a-ZGTPGkDwMVc-UBOwnw6_V1m57Idz73nVCYCVy_6Pc30Yo0Vd6pd9wDxfejTpBQ9bHpIXdHsTUY0XTtPwDFwB5_Uy2tXlcOjESvswhuzP6obffSlq-dlwtFX6A8xf5RCyCVJqLhh8A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (556, 18, 'https://1.bp.blogspot.com/LtZw9DoUYtFCYM4X9gdgg1_QxUhngwEeyk9dZc9xgJcSpAvpxjFo7DrY7TCNA-D1K0kSY5aRgk_hVOlWhrXTgp7Fb3_yA2O4Sqg2pFFVSkyLmWdpFDeZj32gXsyowOL_kvgr9sT_QYU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (557, 18, 'https://1.bp.blogspot.com/d3KWFT0JHeXc3nizHWTUYp_KEhsdmX8zkQkvHUWKwgMe-pLmP4zElEk9B2Y95odYedE9mE58jifMIcF7NzybQydk0DtAUyb62HYBMshemXPE7PYgXpZ7-XvubdKNASJZR-UO86yufLA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (558, 18, 'https://1.bp.blogspot.com/u4kk_L70zovsOhsGi_-eg9lblBlCMv1MnPKxjO4leX9sADVpjhx5mR4PN9WO04CE3U2II2km9ZaXWFK5nHo0jOFhLspjGzMgrkwkUN7oDennKjRFVP9rFq6ULiAxMokJUIfNPYOp6Ac=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (559, 18, 'https://1.bp.blogspot.com/JzBhgOcL7s4sNUBKrpfViLLeLVSn4AkQORG4hdqzvVIUIwK2G7tzumkhe1xrubU1ATxQLKU7c1UhfoRPH1BusnMZ_UOoOOHUPNpTSClkJWq4yH7KehdeeHc-HrAxvfNfH9wTPrL0kWA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (560, 18, 'https://1.bp.blogspot.com/HCvAGJfVKR7nGix6Lk1Ie4Wj5EKvmq0D2jN0lWuUx_rv3QELAFFoGHfLM413LOWIcn6ys6FuH6svtCsKGwbDTWit4qmQRvI0WZ33AWPP9mRAFjs98MgjmOpIFih4tArisx2yAJvEmnE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (561, 18, 'https://1.bp.blogspot.com/tHWGmv6Zd68hpuX-Z8ZrVGmEWtuTQtvtpr0k8SpMcpH_l23z2tzViKsjrPK8qHmtFw0TxJ5eNjKSyMVn6Lo_BmXpE4DzvXsUPnnZZxsqjNADw7mqDz88e0oGOhY5u9crdAYnMgj_Di0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (562, 18, 'https://1.bp.blogspot.com/tj_14eLwX2xYzYux-TcIteXTA31O9u_TXT8KOMmla6lOSnVlIkaZGme-yAxff-47Lc8G_1xf0X8aNwdBknIPWsRKXmq0nYJol1tUOnHe4iIk9YNKkemCkimIYtO00OZdxcdtpcJR5SU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (563, 18, 'https://1.bp.blogspot.com/e54bWuwBsRV9lREiwpQDxbzno4TXulZNeXlhSDy6QG13M8Vh4qA6l4a7bnVxonrHeamRuA9xvi70ZMD5XHuI-mQNxngS3YHFgqkDxl7_iLLaR6QLpqpXUqwMYqg3da4VD6IC9OUwIc4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (564, 18, 'https://1.bp.blogspot.com/k0x850ACsH-tuYuvuDKIVKnukrpg74Ccegd0DTn8xppx-s1UIusLriWl2zd5nBK54YIrRXmOpQibToiUmdLNjSQTo1Jt-9wtPG7CkMZir_xp8ut_Ti7On3dTu1dev_nPP3fFqy14Hzk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (565, 18, 'https://1.bp.blogspot.com/cqhaXF15XU5suspLtAI5NMvs3B7cGwx8qHtPgQUvZQsshRcrdpLc4F1MuWRxg8iudENacBfLKaqxo79zq912kHD1XyAVi0kG7OCwmbajOtMPWWj-xCfe59Klx7_xz5CzY71VUVSczvA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (566, 19, 'https://1.bp.blogspot.com/frNP7hck4tZiIQ15kbtMgkUx4DHuhGz0FUHOSGtU4pUZJck_L_eH4EUm6GqWffZzeUIGjq4_GHDYcbx5NlLnFc1-2FZlFEpDAdFJI2lAEVUOjlUIHhB4jfYY7q6QdxJ8Ks2gglaREA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (567, 19, 'https://1.bp.blogspot.com/UiMLgR3mGmD2UbNRZH1d4i4Mrf6aNXHKWvKceZXU15harrfYlk9YxBTZTVptpfjYGI5Qzxq7MzzERH9QCkvKoyh0etpn3I8K72t46bphdpvcr8WajrjrTlvYH3z5U_ITRw-xAt1ZRQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (568, 19, 'https://1.bp.blogspot.com/bV9ERsxH-T23_ma0Kw5ieHPt8AZEoDN-a2oUg-9zHcpkzeQGay0vwV37dGVTGg7jKDE7CdWGs5yWsh4g-Dzt7uo1496vXVr-a8W4zZLB862ZE0YK8tptkiIBmB5Ft_27F3_FoGVeug=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (569, 19, 'https://1.bp.blogspot.com/nYUqT1KXgV3QjHfu0lp5Idkb6pVTdJaObOw5iOMNNEf5Zt8IPS2exibODEm8lw4Nr7Uyx2Cf1LNFHDbafJWtmhgZCGEKfbloS9wdlQefGZevTsz4H_7UC8sNOH3xz3wfxAlkdkVFFg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (570, 19, 'https://1.bp.blogspot.com/NrGC96mtkx3C-fGEcP2EBYVQg-3h1jjLxKf5XyOYce1UDl8g1Iltb-Mz4d7utl8QvN4v5c2JGbdYfkZTTxkJHVb9IFZpVYUkQ0e4U0BLLIDWneqFPftLoaTJLuUl6kX5Le1Ft8kWvw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (571, 19, 'https://1.bp.blogspot.com/IkUBvXfhRbNVtuNuLwILyR-cq8PxFU-13tWN8PZtXHWQqGeLNm2AZBIp4-narL5063_kPefMLm85iU9uRO-TZR_fItlj1PMBjtRT41RQ6mowdlHJYEzU61056EurB4wVp4Shkf372A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (572, 19, 'https://1.bp.blogspot.com/cka30cgLsciRIgjR-mYuqsnuTtogNaM2HEbvGqjHzsOTw0yjT6k8_g_TuL7S520PPDbbbB_sNbk2U9eIV7_uR7hoeMKj6FjeL8FrJlspa0qIONL36snUBAywLiugJ7D6oNSgo8wH1w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (573, 19, 'https://1.bp.blogspot.com/QU-PF8GrQ-iXEK0su4mKHI6XXyOoV8fFUqeLqRuDedwDhnVMtEjIgyYAn9L-bkLqFN0ceaeQA6tCoYhC8wZuVHxiTWfmcyNVNSMJOIctRg-g7ZsYl-_HuE6HUdUc8NZcitTUDcZJog=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (574, 19, 'https://1.bp.blogspot.com/Z124ygcPANh3aSfrXgmgNol-cuTpWaAE9ad6MTeVVqegzKXcMeotIcZ7tPASGOQsOSGQ36e_dMSxH97BKj4yJeyCtG1kpLq3xgnyyF0uWwEvF-raj1xsdDFuvh-CbYrrRtxXd3QR4w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (575, 19, 'https://1.bp.blogspot.com/4ebUmZ3pxIhT98pTkI2QZcwTCXEk23q2er6y0jTx4-VTl1tXQaE0v7aE3NIbyldebIipqCgxarhFv6OqbWRRagt_q2Q5f-J4zhmPTu0zs60udLc3kuk6b5nP-j2LZCBP1Q0CPDx3iA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (576, 19, 'https://1.bp.blogspot.com/9aDQ2903HRvM5IAU93w4znCCV10SCTBpYZkQyhSjwVl9JQ7Qfnul3Gz6A87mYj6l63TwC6Sl1h1IOArmVEpae2q-H2hbPc2VDExvi92MD3FgIuWCVSaBlEAvp_U70X7GpAqs8KXHVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (577, 19, 'https://1.bp.blogspot.com/BC6-Ji5lTZcvgzpy3LsjL8Wfnaivcs7qv6D8sYB0bcWLTuAmPRmH7Ie9TeBrfpzUu3gHbwkAFcT5L70_3Z7MAXAJxF4FBualuv-ei3RUwfHcyA56eqdp9CR20HSybh6GtNA_Z-QXqw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (578, 19, 'https://1.bp.blogspot.com/kVMaMnE5CBhjXmfYYoqtomdM1VguXtE2a9FwLj_DYj6NYc5Myewgzq2E1OMNeNSQXGdRTP7DgAOFtntvcsYgYE55WnSyV3-mmC1G8cn7bZ3C0inwMyd8a13qMuhXq0WPEsGl6r0TeQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (579, 19, 'https://1.bp.blogspot.com/TEuqmFCAexrOfP2O_cb0aehulALdytRj6DHIN8xEmfnb3IdPfKFTIiKpEn-E8B83YLlOZkrMs0QxPFWELIpx4qIPZY6F_VvYEa_fwa3lOpSTawzMrg3jGYQkXbAlz-W7jGtfBGaU6w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (580, 19, 'https://1.bp.blogspot.com/IWoSvezMZdyyiu4vCMG2CgpK2ws5XmMfX_U4A9qiNnD2kxYbQRfpF8q6W9O32Yb-0WIijj4w8G7spbRk2iWnZSvex78vZqjXWatN3k81xwledLmACZBHJ8oNjoLulRjyTZpgI1yovw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (581, 19, 'https://1.bp.blogspot.com/LdJ2yOZm-QKU1LYgIQI40vDu0XMBvtlT699IThJg5Vn0DHgFnPWbjUvtnMH6XjczE7AzXDmfyg3NkFyM-mCaHmsNNsCtvzaqtG9nrby-UyHIHIpMxKmlFRJq-zv2_XO7Fx7PJMHyzg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (582, 19, 'https://1.bp.blogspot.com/K8St0hKFqFH-5NKcPJIngPzjFN3rRpcFc7OoPUqFJWiIXEScY5FXiIXsiizAih_hF5hevFiVhVobyVXNAPC1eYj10k9YiltXKpBeyacu6fu6ytIsvqf3Iah4Baiyb35n10jhWGVJuQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (583, 19, 'https://1.bp.blogspot.com/vxxiRf-vpxuTaR3NT4KapyBjjYWAP_3rzo_MRfbAwMFrkrULrjdL2qGtFeheQGfmADTZoTnTZ3vSiiSMyb8xbfqlXpmyuT2KkYw-VYmLJLEkeL15PjEnumPyntBpe6NgZvEr-axoOw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (584, 19, 'https://1.bp.blogspot.com/WseL6QrLoRqdjyO3XuqIERSXC89IB9DwZrIYpZa1Lh8lawrt28VICNs9k4x5x9RyLpFiYxi82myHBaAAAc4OrOVovLSfbEA9ZIuRmwxah17wyeAdNjL9iJRFg4POakzrzUDdxhBf-g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (585, 19, 'https://1.bp.blogspot.com/2gVytLGQvWr3Q33etewkjELj7Op4QZGeVElphIy-739GU9Ko4nBAwvz5yg2MCsIGtWImHrTl6cpliITQAbr6Yp6EJz9YJMHa88THJJc1xq3KMFLpTGXxR5l2WcSl4lNc4jOc-gtEmQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (586, 19, 'https://1.bp.blogspot.com/W15Fv8rt4ZaYv5Hb5JmOUqEjaW_VZ6gPd2Z--ZhJDNUrZGTWjHhufAKYtwBqleTnOxb6DrhNsB3Y7V7Csv_D_wbxoyi1oybo-iOPAH8kfziCnWWB4BYFdc30r6STOzfWjYuaBK_NEg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (587, 19, 'https://1.bp.blogspot.com/U7EfZPtqMuSQ6DmKYU0tDXFYwxFxIpItw1ncd1WJllipZLWlMUFg0_LxRTuDnfTyyIEmDZUvN-RDKgVvaw9AFJeY47WQbPB943VhTbArrDs6kSeKYRHw90bNii7TkfGQkGk4fTVVZA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (588, 19, 'https://1.bp.blogspot.com/joCg4sGYtGSgcVnGd32q_a8ZOX4lUFf5bNbGJg_TSK99rfAk80q-LYRF1TtO-WFyTcyx_SiR1EVC5qIcSqSJ1z7pOfeO7DN8nQla__1o76MiktHfxXXvooi2NyZGw3ZY1KT-gt_epQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (589, 20, 'https://1.bp.blogspot.com/PgtG7ky0lW9B5E8nlH02Bjkvj8UmUNVnKscVVu0VO3c14QIXYlo1T3RjB9Vl4kf29_TypW6Qoz9UFlEqQb55cSrbsJLvte6_DUSpHyqgtgGBoslK712WKZQSNIsohySUMIHukS_uiQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (590, 20, 'https://1.bp.blogspot.com/4iFsZMKdICGjaKKy-2nVnpLp5ySM6R7-7Q_ZWhN4cni-7LF7rQdEWbkCeMPT0gsLGRPzWh1i3369iS2VFOzJc01xaz0OChxGTJE_lc4kVqyEMEWi6XEoo1SAuqYbztn-IzHGHwp-EA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (591, 20, 'https://1.bp.blogspot.com/EXNmkS8YPAD-QFfb_TWKtahZvWc59nqEfzO9QLS9_ozw3fzm_XfK7X5DoxG1d5VhQhOINkSr8U8pguYwILUg9dqnmAXtw5dWMkbyfbi0TbhBefJLTn2pxXtoQFkiCkBOVaZb3CQlpQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (592, 20, 'https://1.bp.blogspot.com/R_H2HbKiPP5OSSbYebskJPfUxSjHuoqhIrASm9iycTzRxA0u6p0yDEAGzDjjAYP98UY2O4-sfhU1b3cXezIHAIkyUhIAPuPlLSIWZEPJJYUR0A4uhUGnqb1rLEkw4HGyKm4VXEK_1g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (593, 20, 'https://1.bp.blogspot.com/mMmjDo_aGo24WY4_l4IuS5ITE1FRTWQ3O4XAWMc-zd7LFtU6VrC23Fqq1yqgJ16Vswp05ONj7EhK8xqgUqyg4c272sZN_9Ro7haG7RDZJlmtjj1Ifctg1-3U7oKTGqH14K5MKo4oMw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (594, 20, 'https://1.bp.blogspot.com/K5xnf9xP53_f07G6m44HV3yV_Uo-5iBZqGr-tezsuRKGd9GDzE2W3_S5gmkE0q5VupXXT_os3WaSuZ3dRLVt-ZDtiljlI74yqQhIX3HpIC-inZI9Kt4rcZf87URO__ulp6MhD0b0Cg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (595, 20, 'https://1.bp.blogspot.com/QL8_fep7jvHG9vgzDEmfc5AsLi9ZiiQXTpgFc-d8e9QmoAiQBVJ5LfBMeifUY01gncZt9MMf5RpCWeTKnQ6f2fQ3y20RFqeDFMhbIq3NqvrK_pS5hGdonmjdbv3r9ao8k7E0cS-f3Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (596, 20, 'https://1.bp.blogspot.com/3-qeDiLnSaJkQN-LB2p5n2-y_MbXxYlEMDRkYjcCWYwMczKWq4DlzcZaekz82JclRbmSiu90RVapbSEu0UHJEJR32tSGfpA-b65TztAJdczVSQYn74e5cusz2uTTF0sM7jPUqKYOaw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (597, 20, 'https://1.bp.blogspot.com/44V5xiDdj5vCR3YFJ6AUTFEAWis04IEaiYtNpi4s5xgldO02rkDS8w8dbnVxUglwzjqyV9WqxHlAWLR-pJlWe_0EzpeU-wGSiagNuwpq_kFJ-QcNOnxsm7nUl7UaEZSCAiZVuyeKaA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (598, 20, 'https://1.bp.blogspot.com/OuXTGyV5IK-L9jdCQh2G7XVuV6VJrKl5b51t46KJRMa2b8GuaNRI3GlfPjaIAuL1boDLOF0uJIKlPTvb87TfB8zP8T7NmT9JGKcGNt41H8pb8pVGQ73NUHxxjHDEAFfWCrQYL-NpRw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (599, 20, 'https://1.bp.blogspot.com/UJCJ-dHfjJEDR9cM5ExsdHOnohSE8GE-ikOvRvvR3EgGyGrWQnt4f1yWF94LLjhzxLKT-UiggoEsEQGib5nEoMjwSJ4Fg5b-I3W0XnIi5sU8eblck-QFOUI5R8SQcGBwSmyte91Nhg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (600, 20, 'https://1.bp.blogspot.com/aDQRP-R_SWgIMxEUH60wOQJAsK60IKkai5BwnCe0eDNfcktLL5Hxf3dQQlNAoyM_mVWPWZjhJWu1nFc5oia77dM8qkufHHwKAxCI3QzocPrsi_ByZ_uMPBa3u9Et3MwMsdFKyhUqDw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (601, 20, 'https://1.bp.blogspot.com/iz-ZunuUAF-eI4L4hI0Zh8EqMvAjTOhqkzORv90n_Ag3mdkKAg0t248F0hDg85MlInV8YdYqjOr89fPbd5cKRcyrcg3vMQ-3E5phTr202mXfaXCez8qcGnZE2Q5eGmLxtKgTZxOzMA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (602, 20, 'https://1.bp.blogspot.com/DTMEUYeyVKYIzwt0MrUbuzrTncT8WIUozQMGVRBrLhXGsFm87oytU8ie1wq7UeloO_ZjJJJUxBn21zY6c-hwdeDHNoi3b3rYs6K_ZUUs4_OgVJ9aMi3_5mBsJR7mzxJtfhsDT6kdzg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (603, 20, 'https://1.bp.blogspot.com/g9iSy1xcLEzfcrr759x323zCg3G0FbEcS4wbDrKPstTaww8kInlS-NuBIdOBllcjNQ-dI6BPV0udqUYsthiE8tE58aXCJ0TrCC16PrBUMMLNMcTG7V6QbYWq2AECRObdOuSCYp0BuQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (604, 20, 'https://1.bp.blogspot.com/dJzrxJSsYCSHWzlhdsFY5vhgKKrJ-2LnwHxRvRCpGmZiCqhQFKudbQzpauj5k-SLyFYAnJ4VLhCVBidftxLm811z2CsZL5vk07wCLMS8Kqp6GilwB5JZDoUrriq6rI5N7dB5Er5dBA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (605, 20, 'https://1.bp.blogspot.com/ijJAyyRN_Mppq2wV9r9PaGbFg4BHuuzeumtcCHp-G7dBAX1wvTTwWh6m1BIXRq-8en8Hk6UKfcxtWMR4lrOjFcPC8_m3yH51Qne-7Q_AHVSVBBfpDL1kJ6TBxAgYLJJn6sLOedhOlg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (606, 20, 'https://1.bp.blogspot.com/YiAdMky7E9SxAMYmrkUBgI71WGYpHlDJ6Twnf93ws5XGsHKDebR6SXk6lO5qzKLkFeu9crQcIlSQYViaJtUx1HqGlArDuWgRWFZK8ZS3hLnUfwyFsPpP2uBJWHHVmB8pLnHBNbQ4tw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (607, 20, 'https://1.bp.blogspot.com/Emwq2-G5oqhplNKboNCSwNSMQqF7c5PP0UdOF0oRa9_CfahAD4pUXnVSHF5yJ5opH-2U7xDAE9VyZdbgy3P8AdAgpTcugdbCyn9S5GtZqfZObzx4jgxr3slveNFpBSFdW9HdgdS3Qg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (608, 20, 'https://1.bp.blogspot.com/MAL70APnVF3C_kO3SSw4Z_QrCMhAVJ6krc_tRzBQXUMdH5WPvX69OEGVrPTw-oIaTiB-RhZY4hz4W4TRG3umsvatGZQv7YJYt8NHklTaQ6XxZ1VfHFlf2lBWnZupvsLOLw-1Rf4w8Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (609, 20, 'https://1.bp.blogspot.com/kyLdowKN6gfJPqA3HjS4IHrgJ7GFVDpYTA58Uj1tJz4KQikdctTcdajt6V5pwxlv67-uGaMM1KxTgnkwEzKkB7-5TXDx-YuehnotkNn6EgQ3cqct1V9CA7jA6fOJ2N2xA5119NF8YA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (610, 20, 'https://1.bp.blogspot.com/POTv7dQ2LELr4JGVXwP5R38JsQwUoTvA3LwnHZdOLkwNtkbLM-I_ULz_sF_2plHXnktP9i57MK_ik4G0cxbvzlyfasc05v9tQm3IHICtgh1lWuvdKCKAgtLPLnXRvbaMffPfKkXiEw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (611, 20, 'https://1.bp.blogspot.com/cpqpMb58WVpyiGPkaQzUcPLaKeD7RI_iIcLayK3eRLs_g_VpX6-m2QWwMJbISqUrJNDh6N8j8ASVFSTSZWPslVYXaFyfCfHVnFf7MzZiIxh88qkN0DaVpBR9MTprzMCGcuV7ruEQ9w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (612, 20, 'https://1.bp.blogspot.com/J1YT0cDAYCb_9nbLxrmuaX_aibx5YfXvFcB2BE8FN1Kp3zc2lMXWKD1G45UoFIp6Xhr2Ej8k5QgGy5hxM2mwXT8I6f0npRUEv0gMJYiIu2CCqLJMsfshxMWR7cBUpk0hMZZXpHvUxQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (613, 21, 'https://1.bp.blogspot.com/I9rjPZ1DCarTW__y9jVaYDGwZsUpSUJ0Gtosm4ZZ8BA77ENXWjFcFHqLMqh95JZriuGFtE8jkBMN0198lsb7umxJsnpRvEOoiWpq1w2Kvk_WaDSNQjKsrAdUmBUZKCEpCYN1uX01Tg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (614, 21, 'https://1.bp.blogspot.com/yA7Sg8u5edFE1sG6Uyfq15ZrIdmQWeDPyawD-zfsi-ceUfWDQNP8XHJm93GxgoX2AZONGRKE6ukDAMGiWbQ7QUqID0VEuv_WmCD8u6W2mQdZlWGG9KS6TeNkHv3EdPV3iS3ZbvSGNw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (615, 21, 'https://1.bp.blogspot.com/h93ycysnsKFLlmTjl6z16ja9HgUqtO8THx5iqBn4QMgRQWBqcJcdI9WdgkAdpecysKiNY2pwg2Z3U_ZTmokRaN7v4457JN0vYfX19bTuI57Xj83dFgVf2jXBKsw04co2n6OSDhiSvw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (616, 21, 'https://1.bp.blogspot.com/gYnhSNmETsKu1jrT7y9wy7eTYJJbjYNUzLsPjbrUdIfpho2DHKSvv0uBu96uNxTvvzMckpOxTSnGAI8NNEa9zbi2W8EoqhmvTHObNH0ZwPIzpTM5rXXfBVjAiQ1YQ1SbBsDjjDNIeg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (617, 21, 'https://1.bp.blogspot.com/K6T4RBMs_8j19CjO5qjCixj2c9k4QQz64DjYgoc_UeaD32gINTjuAA-91CfSl-AjR_uLTwO6DH5TZUskR48YJJIEjVt12r1YR-B6gZkwTIN3SBI4XmIls1AVh6LubcAWGuO-ytS0ng=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (618, 21, 'https://1.bp.blogspot.com/iX6mtwqy10U9Cdil_h8l-kDN71LR5TJpVGFclKGfi8faChqJci7n5YU_D6QuU8XXIEtnBa5gO2fn_zd4aPmQnBejZxHh1HUg1wxpe-L3bK3YTmg9iiBj-5Q9vOnwD0jFiAtH8dmQ4g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (619, 21, 'https://1.bp.blogspot.com/JwICS0_k7tPd08WeKk9ot5XDHaRC8kln3jW_ghkl76fmxnVUJaJedxcd_uNxsGtWD-I6FfbXvOHmJGQM7AEJCw302tiIht3Gx1zb2frt1MN9zDC8-ny5EHALGzUZO71KDJY1coA22Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (620, 21, 'https://1.bp.blogspot.com/tWnZC9ky2ATIddHXOlfaQzi52ZhEmcJ5Pqrj3HXRDf5P366MDeTMn0LsB_f20_LmI1aNW0RQ27hEGEf3PH6KqymhGzgqIciTlxTarFzeiLVBESe5wWfv925qZZbRDbViyiqKsinLGg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (621, 21, 'https://1.bp.blogspot.com/AfiXUVQzaZqxhRerPHpNAz3rfi_5--baBsNBnmX8MP9NZcuIQ65J4rHm_oAaAHk4IrHS1qCEX577E4_0Oh0wCQ0Jbuk6rMtpI0aPe_0wF7olJC83O76Ebs-iCSt-_Ulx8QYYHb5f9A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (622, 21, 'https://1.bp.blogspot.com/OvVTKX9OwbG68DywOL4v5inPopjNj9vi8snXri91N-JyMDcXe6RmZG7P2R9qAE2rgcMosx-7iaM8rZBsq7M5AViBIK8mbtFKJ-AeqAS5-t7bIwjTo6QqmJOA0orE6Q_OKHRvF31jAw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (623, 21, 'https://1.bp.blogspot.com/KRicGaL_p8BtPyFFauVxev-hPThBxmH5LBMuNWxcDXEIl5_0sSZqN_BlwRXGcZu_16zW42sstTzauVrOI0JJipMp3-s1GuJGlQqn1F9OfDW37GI8QwhOEjQ5JJbgUGcCT86L7GhnCA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (624, 21, 'https://1.bp.blogspot.com/uPAFzSlpE6aFvEmI7YJseDD4ey6dXTL4ZSuLglIdDC_5kPr8tbvk3U1VOqr_4HFqfe8CYIDiog-g05-mUF_WuFMsLmbWRm2a0PV1yhWmKZNkSPT7xcItNMAXFzewhYWxm2E3SI7jkQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (625, 21, 'https://1.bp.blogspot.com/X2hU38ik0YyAf2HTYDK7E40jmIbDMoZOBDwAZbPoYXGhJlteioscv_F7884pLIH7z7JYxsFKiK7OM77qI-v7fJzizlUeaSf_lLnnHrCteH5L8kU5hHOkwDd4eig09IwtrSqaNljsJw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (626, 21, 'https://1.bp.blogspot.com/nRefqKoCSoxEkOxfFhQ1pp4zoYOzdk0DTPGRNJDJEaGsaKB_9RSKa5h2UrZl5OK-17OWLoLsr6LY3O0cW0LiIGLSjH1r5ymYLpUPIldr7Sly_SkEVnLLFb0a6Gscu40bclK34jrCtg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (627, 21, 'https://1.bp.blogspot.com/6-qCexvEiIOuvdPO0rYkxI1DUyb9txMmPADz6UUGN9cX-cJzDYs6xbqqfnEb3bFPNFNE2ZbrS7OySIiOzPeJ8a9bS70g14HwZmLPS5X9laxTM8uYDSxxNvCuAg43AvO5pRauOJ37qw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (628, 21, 'https://1.bp.blogspot.com/4RJhHIbn9BkPe8Puql8q73RRF0pOHLFsfpdjlKDuwlKZ8vlxCQnzM3ipVwR5dr4IBMqr4Ztcb2BUQfaTPbnU1cGPMRSq0tDDEvF1LOtgRQC-b2_yRuVuAwYJLRfge1ZFlrlzuBv6xg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (629, 21, 'https://1.bp.blogspot.com/AYN1fvlin2y9vLBMxfat229DFhgBYkXOhgV8j2961N3Q6Tti-hp5Ugrbr8Li3FrXgnaLuX8qR26bXZCwMKAom1BPG3OHuWc6Ev1V3FdEvz32M-DzA-FK1jb6bUQwG8bgvP9LITPo1A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (630, 21, 'https://1.bp.blogspot.com/HRQ9q6WDhc8mYHb9PDVxl6XNeajREVqO6P9H1-_oeo8baeZ09U-X3zbwXt7iaI7irrwoUS4Pt5tdV0u4h2NktE-A9Z8nqrFEX398CXaXxsqNhWDBSpgBJmxGokEwXn_R7z_CsBcEQQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (631, 21, 'https://1.bp.blogspot.com/A-AEHjF4tDK6t8hpvaih3Fwxqb4vyZFVoIYiy8wRHFg_0XQ-WjGORSePw3XNw7cXIMJ8bxbG9DZkuIjAwOxxpWapFEtibaej4Hv0gMY3n25Q2MGnFxNHoMaVMPjYfQk1wFeOXS1rfg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (632, 21, 'https://1.bp.blogspot.com/fAlVoPUdx5ny3Upuo8EI3wK6hZ5h1EBo4htVFLkV3CJ7rD_czCW-2ZfRSNJww9kfzOtxyM1q2PNpQfL5GKD1P4IoggnAY813XOEsqJA3p1oGe3bUbGUMsSPB-nV2Sq4ceBu5B-NNHg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (633, 21, 'https://1.bp.blogspot.com/eWM7-1uOLNBdNKcoBOwGoDF7dIJtzF2T2HYe4gEkgXdQF2ejd3oXzKd6MFs0SHnPX5CitL6W7-OlTsbbCYWT_qGtUtjmspuDNG6ZBcm9VGTtwpjyqL0DubWeuYXpcK-R7XIWMkMSDQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (634, 21, 'https://1.bp.blogspot.com/NDWv5dfSMJeFMslKA_0cNUeSe2dJ-YyjEtIuk5OWk7rt249gti751hOHeyrdVR-ELt4t-HGgO_gtaxJeygvAZtpQnQqlHzRRcbrweNj1TJ_pilS6UYBo_zFu4-E1Fb45z0fso6TQYg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (635, 21, 'https://1.bp.blogspot.com/Bz7E-ZGIccQYuycuvMqk9LrPeb1Y7bCVfDBaKmQ2qetX9W2QeLZ8DhkHXtdlMSI4OA2mlhm25nIPYwIcU4Q8PdywkaTzVfDemnfhLDZUWVuR36eNsh-6fMPuJ3LbRFNxIawhbg245A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (636, 22, 'https://1.bp.blogspot.com/0_imvfzpFQR3ltecvwBb1NJrm9AwIzc_ILBv2TBUBWByCjcZApSvGNv0u-XlqAo7J-idcJs6V0jds6ktdjzFIOwlShFDnb5S-R27ETXZMoFWugA7ePBmSPJVBNyNapiSuSvEl4JhtPQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (637, 22, 'https://1.bp.blogspot.com/Odf5UyX5mF5QFhFv9Cn0Bh_5rIFvFUXJKsycwq6HEYn4ds48emdyHt9ZWS7BZ6aMRttE8kfdaJuxEqquAbWZDy6nBAWDEgoZqyyxC58msrcMazM-WJI1nOZMkozHVhG1u4FZ5XSYvHc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (638, 22, 'https://1.bp.blogspot.com/6FfkjFEspO0cJGTgKflCX0ky9uB66WE5Zl1yNbDTIGhJBwmYJ6NXQY7ofMlEfOLhcUPYFtrac6GI8Xh3PSuaSoH0UC9ezQLEHdwC8qPps2knZ-CKnCoXn8cKlvAgxa1rutno-pzLZRU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (639, 22, 'https://1.bp.blogspot.com/03C7kxXNayD8nQA0ns1flovqZL4c3_TZvZSQtoORmnLaAjv1FR4PRnQNW9KznZxYGWOLj4qJqZ1iHa5qY30O690ttrdWbUYXLYMcBRl3sxJYOSdcZeAa6KVYbDgsVyPaxj0BIk0nLq0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (640, 22, 'https://1.bp.blogspot.com/CIbRtDvJFFE_agwjstbkcEyiPPx7tZGYEC0QILOLvmRHV49CPuTqu27yi0wA6s2fDkoDARZ_zKvdY3qRX6xH6O5L_46K1gz0fEUKtU6ongEthsV36Ns-ju9j0XNpOaJXoZ9u19FWb2Y=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (641, 22, 'https://1.bp.blogspot.com/FmRe3Ue07tSRKLDUTRYhi567fQEa5B0KSxupgnPydVyDgdWKGVRApLuMEJAX41brmOgXFNWAV_xoH_ldaWNPT5snAOKHQycoxx8WIqBlZG9LelDXaWKRySNITM0Dga94mcF28WsUvNU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (642, 22, 'https://1.bp.blogspot.com/3EoTFMyTaQWor6r65xbhJIQwY1KELh1_xhdAe0LtgjmuJFrbYeKXsGtb8aAGro4uluzlKPm2RJf0actFe2yX_RBK0OzSXVdIowXbqWWyKLYcLHXsv-JYe5FJLi_QKV6Og2z1HwqOcrM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (643, 22, 'https://1.bp.blogspot.com/I11_BKvjLaSdUCEkzUS0FvVOWfHiiFP8840sd-Wc3hRtGrzYqajFOfznffBYKwT1uIU6H0uv3AfgxBwpYxIcUOKYi6TMecOMv32Y2P9OHaPcsfDrJFsgTIeIb9rtdIfz4k9cB3Y5hfg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (644, 22, 'https://1.bp.blogspot.com/l_5Mvd5lJRucOlvsN1GdPTxgVEAT4Kt1rNPh5Gy2-feUI4-ut2Tjsj7XplBMaPVfp8TshtLYYPwBRDnftrGD7b8dq45RbrWuteCs-C6PQYMR7RfVloDQwxbDYyxJszqOi8xGa6OJSdc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (645, 22, 'https://1.bp.blogspot.com/vNaHZbG5LxKvx9SGThDYJAJw3ei8y2j1LiwQhO5kJPNQpp9SijtEkTsVtBIJZILf_f3JAos1LMikEvh-DvHT7yQ5U-k1HeTvCQdRQCwqi_y2aBV5QeEj92Eu-IPNg98rOCIijqqQ328=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (646, 22, 'https://1.bp.blogspot.com/4nRB5b4_S4tNtKNixGl9eAol1TDtqlw97BRUzLr5nTJ6SM-4u7JlLVM-oDt3xqHqI47azSw5cGaxiETCl2Q6nV7TZbEBhZsOhYECKrhJ0Y1-FvCUJo8e2HPQbQ3MSTsC6AogyhoBUUU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (647, 22, 'https://1.bp.blogspot.com/5kSFyuJsqrT4fImTAIhKsEMYUBC5Bln4V0G-P4_duFReHbyAnie8u2fswcFxHkRqtp_DSHC-MsiWo-1J_GYt4-hqHpwjpeNeP4fwntAI-EEoXWGws5xGZYl5aP9OUIFoL9sENZDsyNI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (648, 22, 'https://1.bp.blogspot.com/bhjBOjm7GBvmelGzd1IKvws9Uf3IRbHBBirETaqTUvwVb2hQOW-eYIXd8knh6QG0qyGaOEVGJhQfUwf5DUdM8RTChtF06ywd3uyQE1Mb_LUb-6SE8AuqVoZ5Oc-modajF_0QYJBiE9Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (649, 22, 'https://1.bp.blogspot.com/TnydGpfp5yDe2oUWW0xRIMf94UBlf4brc0NR8bJ0GX0yU7FdBLUb9IuGQ_QATTB5ZNZiL0pfxtPdUQAjL3wOTmYDDhV6yh3kVF4ONG9EzVE5YqwpInA79gSZhEo4tKQI0Hy0nP1oFEA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (650, 22, 'https://1.bp.blogspot.com/XchBDcEY-qRMJK8bj5zyqdsplThD_KGhA6NpCLqx6bSzqICi8lxuy_DbCYyL_m-OBWGesnLVJo1LqSsZl5aTWQyV4airZtQ4Ee-5xDvvCuv49AK8ZXtbvZATgqnCvT6yLIxgaCKQtTk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (651, 22, 'https://1.bp.blogspot.com/tVlJ7XHFEqUJJAsUiZtW0nYG8Nr1hnCsEQ9eIkQbUXqKWvFHi4b5eSAfaIC8kW55_ZRyEkLbWyfZIa4vT77BSRdCCrwbNRRQiez39cd9UYftTBEFR7cWq18nKJJk0yz5TZUsPC8PoPI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (652, 22, 'https://1.bp.blogspot.com/4g15iaVopr2gPE96Llq2Fx3NaLL1BZSh3RAqc45IyPOViTiw_lmCkTYDfA1ONr012_CqksAI_GZ2nn2RdwvFOoeg5FuWmIPEQ-ybNWIQokSlfsbi0lTHgxfVqw79bkVsLlZ-9AFErl8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (653, 22, 'https://1.bp.blogspot.com/0CuLd-E7GXNPf9ojYKFlKQrpa4qCxXeINzayTRXHow1bi7ktPeY-pCsw24jf0tw-4DaDHP3zhQ_Rmxwe12h-6cxbK_z_N1jFA9o3ympgEFZy6Ti-yy9BYx9gWpGZ2Qqz6f4wviM5d5M=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (654, 22, 'https://1.bp.blogspot.com/f9VXl4CKNBaFgu76FhIaedoTzwh_MsxN9ftoNouoG1Dx9-OrO1UYJS-xxBgDRHRLAvNxEN7dzoYU49KiH_lFAE5AWFHBej6fMcC1qdlq5jzjNAR1dNNVFy6HsW11zSo_Q2wL0Y9cCsg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (655, 22, 'https://1.bp.blogspot.com/Igfzby4ARgn3kBB7AHfy8ylYOCm7bcG6PlcVhDn23RYgXQWRtayf4Rl2OADamGc3KssK5noIgNnpEs6N37TRyoM8L4Fh6c0oMfy95fFt7rd8zuMtwWbAkUFX6lsh-jL5leR1YmH2-zk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (656, 22, 'https://1.bp.blogspot.com/3zwSRqr3QFaxZ7rNoJT-Hm0B8BmJ5L2oO0LmfdYOwe-E0OPSRqI8hHySE0Wd5fCbItL01dDzhiQt9VcDRhjtIQ7V2oELBOLa2F_lWH_TjoSfkgpjjrAVWynvsLtCMzhXxmznA7HjQ5Y=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (657, 22, 'https://1.bp.blogspot.com/jj3P3ClZwPHq3Ni8guW5rAyzbvz0Jpx4FR-skkcxRKDwBP-jEphF84Cn90JU3hW-KUG00Rdnu3Hh4H3MI7i5ljR_SZTG5hC_uBMoEjGnaeDFad1tlkA_AdczQjx9ZY_wu9EQTrf-Ngs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (658, 22, 'https://1.bp.blogspot.com/Onjfx5uYEwAMCskNX3nt6oeiIOHv3HnxUQzziDOpP9VcE2xiXhlzEaEKVl3s8WzXkzgEqKT1RGOJP7r5yfLeANVIAv7VVDmitLDXhu0uB6trlfV_7HVfsUDj-exUQzFTzFN8BXl_3C8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (659, 22, 'https://1.bp.blogspot.com/USIK695LvzOWX71Y165KwSB2hTYAOn5VF7pN_3Bcavj2qi3VyFFXb8cCoa89kkfiNBdYXeVgGMTGo7_DXfneoHjN5b0Vhj3GH62amRL2Lv-4QPM_O90aztltSqpVMGfKD1NCde80kL8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (660, 22, 'https://1.bp.blogspot.com/jlu6jK1LkAQ9XAXx733-c3ZCLmpucmCurJafPRzTkravEAHH3x6mKm4RjRqm5rtLDqnso4OGal4WW6dhBq6KQVgLNnBquIOndwzAfRrkNWXyrlA5CzCpc90fOxlvHJp9DDyIMiTrpyY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (661, 22, 'https://1.bp.blogspot.com/WjtNYgNsDsuaFtpKO-G9dlYwnrQ_WcvJtLMgLhr9tBKya3S-fVfPqKYOiR-fkL-o1WSbT1dKw6adBFd-EGf8HEsZAB47uCThOREa0I-SLgdbCmmStGxV54bJYGjCVI14eomJSv70CZs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (662, 22, 'https://1.bp.blogspot.com/U8bMbJsprfs94TtAOeBmzmVLDXtcDqBQ6VSoOa9njkHMc_RlfmNvmpnh7oH-apmQYd3nW0PQWMqCftkEDhMkWjscN0FJedCiERFeDvc2Ev5RtbjqisJFtnAHIKP883G8hNnFlFF3s6Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (663, 22, 'https://1.bp.blogspot.com/11rPrvNuDGxLOedzxZNLJeq166sbeu2ESW-JJ0lAQh3-kAFI13of22mp4mbgaUJqUUCvvE9qnxHNWgYrhcJWUq1qm9430yDhAXIjTIji7oLr8lj6j0cgCP_6A8tR53FnrPhFJliBuy8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (664, 22, 'https://1.bp.blogspot.com/02f3eys2_pf-x-Ce7rrIdSytiN7BtL7k0YoTtfnvreU9PI4dvtHSAvaWR_9LeFhlNflJCkDbM1y3ncBHpUaVFvCK0Km5p7CSvQ8ibyfinj9WUsbMz3om1Q9uAYmxj7PhlrUGorixK2s=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (665, 22, 'https://1.bp.blogspot.com/PXcfjHocutlmxmrUOhrF06XEuo-l22PhzE3iQih4-S4x3acgNvSmtBDDhFlmBABtK0kwJWyyjxcKT_KMsjG82-rzBwBYZtk0T2yb3HHD55WN0N3368VsBi82IgSCLsh1NQ4BW8IrFDw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (666, 22, 'https://1.bp.blogspot.com/Lmyf2cvRtKiCXFpW363Qeq1yCP0G2gB8_iVJvZwtgIGeg3MKgKs2_EZAFLcopjqBMxsgBmS-FeNyOX7SQlrERftd0FxIrhZR2kp4zchV8Txlv73_u8KoZcUymbgnBlvjQKiCEcp6rHQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (667, 22, 'https://1.bp.blogspot.com/A38gXi4R3R58A3FMJc3zC70ihR1uxhUZ90xxdXWQn-kaGOVcysvM5qT9tY-owzDht2wr85iAVGXN4lZzUdI1sZDssS6DZT1ICsgvMvtBhUbFSG0oYK3kFqBqgwllOzvzg_Zrlkv_dyQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (668, 22, 'https://1.bp.blogspot.com/HeR9p5e5gdK4ojWB7YxRt0xsjEzqc5ywemZR6D4JKv8FixDfPUo7cHqkxYlKg17q5hUFqq53o2kErkYJaS8jGk5ufYMgywICH-LbsFtBYSVX20ID-iqSLzOvh-PQyTqtpltx8psLB1E=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (669, 22, 'https://1.bp.blogspot.com/NyZ7XGHgTnutC22auoh6TleEzZSZpBXwcYyLeWS-tPaEEf4fNrPNvciDFFa8qgL5lDo0C26cx4I1WGLfV0YsYIAMZRaWamb7tCJw1fKtLg03rsAFXiqKEUL4yfHm7Znx_MO2BX60NoA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (670, 22, 'https://1.bp.blogspot.com/gbCTacWBC8TXSJYBG_OZEq_MMjrGyb7_--XsTLwIepZpUoFsRiL1tLKG5KPZihovPLygvUiPnOaOI8K8ySnjj4iRoGjeB1hWaYDcLe7Ywv_JFPrkuB7cCgbnYSjxFh729v0zldfhvY4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (671, 22, 'https://1.bp.blogspot.com/aGLbm7IJYVAwJzjGn-_FJyWhgqCfStA0hFkmuCK_y7N7kkRgQMJSrj-ha1A8jpiLIL9TCS8bhez4pA5Pa1TVeN7L2C_HaH4VA6le5TK2HIYJ1kH5369IHo_yKa6270PcioBteZUt0CE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (672, 22, 'https://1.bp.blogspot.com/fp65YBvwJ_EmEIc8aQ4HBe2U0mlL4Cl5MTuvGTe5qW08lSpEYblXjmGQ2ilOhRfKQT5svtH6DuEpLZyqLEJq5AMfCCdGy0bUQ2rRj3H2Yseh3MAD54CZVPzVyug_OlV6sAa40PNjZjI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (673, 22, 'https://1.bp.blogspot.com/4h8n4UNo1_ksW2LUllUwcO4xYekpKtpj2Bxtfa58L2zyLz5_wbMbY0HjFrcjEeln8crJZ0VSBfQiDz2hodfkrc8dn27dSeIwJKjqt4NZJmnzu-wJZexIPjh-1DtuqfoiYPIVa4azwrM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (674, 22, 'https://1.bp.blogspot.com/N3AOoH1wIbVqBBQKaDf7qE9AmxXy60AXGZV7UczhJi5ZUdHmTt47t-PK_tuwvzl89ghtJjpIseRKdJOZQEl0gEYhZLxGFs9g94bpQYejYYOWgaU-EzGvbiYseqxZb8mXkwS3upLGQk8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (675, 22, 'https://1.bp.blogspot.com/VN1Nz4jsuJPv9JQuQb0JeWmosCY7a-0S0_bon0zcW2jUhreD9rqSczYS679ekWWOgSIA2L5h_BuJ8KPlNGoklcivlcN23j9uS4Ia1lrhbmcCoKJircWfYkXa9Z0Pmhs83T89u-duvKo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (676, 22, 'https://1.bp.blogspot.com/jO_ZQ5N6s0d0oNe46LenxzKYolkTQGoY79K6xwWyKyR9CSMlENRRBq9AvqXZKQekeuGb6qBKMEc4eFk6f4Nlw10HCQiaPZtLgBiFHh0c7-59qrHwVzionQjEnTrKE55gLleAIU5Iorc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (677, 22, 'https://1.bp.blogspot.com/TJ4-xNPMumBcybTKtQIIbnyY0SbqLBqEMPEdBOKhYW771BhKnmN8HQ2sYkhbvCBHqK5QCkpzAL912F1tKdR_uJVjG5UE0oyYCOrUYtH-FKqseqx8ZJ1kyryWSuK5sOAPdthtHN-FPv0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (678, 22, 'https://1.bp.blogspot.com/Ueh0krB7gK6UsOuPKNwt6yrO1IOqd2HBqAU7i9hpajlNB8E8LAP8Tf2Uzt9vZ5Aa1PryQ7sBR8U1gac9_VaoEEm6OBRkpDp21aUr1eQesOuknpqDP7PfoyzDQ72K62jbl9DYe4sVIvI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (679, 22, 'https://1.bp.blogspot.com/P8i_VKfxvUhqqDkySjLfew7NguHdt7wjrbFKzHS-QLKbTYv2WuS3XGUOOxTAsuEMl99Nd6U0kF2xM85KY1zhIPDqQPr8OGeVybljRu9CUC08RUdDFrALH9Vwgf5EviifQsQb2G-IRKA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (680, 22, 'https://1.bp.blogspot.com/0wb5FFazL7lpzbA8W9gP6zvxp81DXIpb2vkbf_60tkv3ybQfrQuUNSxDi_h27gkN4AyKtrox6xJz9L2BMXKGqpfpHHwROv2lXjZHA71hik6NQFDrCrQHI2QudOmCylfhJOng5rmK0rs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (681, 22, 'https://1.bp.blogspot.com/GxhC_MyzCO4mWkOGtLdODyendJP-nE4AcgtQSa41QRl7T4qDTuZf68tZb95AZVdFj8ds3IpCb8Icl1CxKXRMHkrJ-ZDhKXeAznIA8QeEbYhjS5pDeYoapRC01RGQodeuX-SrGHcSfWs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (682, 22, 'https://1.bp.blogspot.com/GmcDeF-iwc5D2eRuBl106WoV2K8EcQIBRYd-CduPPVGWM0UOJ-YSbCGkAzGXHakUEJ6nfKnueVagUWi47sK7Bko6AJg08egOO6vwrHCmJl5okPM9q-7qkimbSknWTWc8PMAwxH9xMHE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (683, 22, 'https://1.bp.blogspot.com/2ff2EafJU_No05_yqk-5oIJ9u2IB2AWRLAKZJHyW6nnbtw3O0YkAFGQumL9H2ISvDbFI1l_zTR2nCNEy0XdcRpfQkzp6i9q4Fma6cOrbgH_jgSuEhaLFojxi1RKSgmmm-LKoWkdKbYU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (684, 22, 'https://1.bp.blogspot.com/ZEwpU7b6ZNjcAfT4ZDQ49wGQGjvVVqSr3RwXHk0hR4Us7VxjUE6c1UO6PFuQETFqd6jWgn25tVuAOxSldZA2Yk5RpNlw_WoW-6Mb_CcQbo91AKbUdqOhzOwJZwKrif_5JsOnRHPI1BY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (685, 22, 'https://1.bp.blogspot.com/Ai8TL4KtnDgMausaTXbKq7oX1bsovop3dWz-Dy4kvYs9Hf6RcNYZpisjBuxEz2lv-sdsBmA1b-K1BpCF1HYSnUbM0sP-NgFwuAcMMy1uTrmjK_k0ENAQc8s-b_T4XsSfaxECdKmmn9U=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (686, 22, 'https://1.bp.blogspot.com/MrWZWGMAq2JbCMqzQuUMuzUbkZ1sFdEeyaRV53OI7oO5IPU9Ghy-3Ngnpq0v_H4odtCzeACZjJOl_mPe6skUjnVL8TnRL-D6ZkJZMZ-_2apNNPShWa8H7IYyNcFxpTTaoqNRo4YEnu0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (687, 22, 'https://1.bp.blogspot.com/iPrX8xuNSI2dumCGv6FiN6eLV3v9VspQbMleSh8TqJl4UWLJVQqC6TbF7YfCvmF_H9ad2JfWKFM8H3bgKwyYTxWKOmp_P7OHIg-dF-1VDTr9moicq6JcPa01oeOaSwirMeeIsb6m_Ww=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (688, 22, 'https://1.bp.blogspot.com/cY9ftj3ZoURAll9Ee3uU4ImJY4qgTbLIi1N8-ZyUBDotHdXp8RKZW1b9IZ1n6NumrM7fu4YWlg15WU9WK_6cAFVkagTvgqXUdxD_TOvnX5BTZP_O3w8A-6OYSE14dd5iJEswqfQU8eg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (689, 22, 'https://1.bp.blogspot.com/oTTiipn4n18UHF9jzRQSTE1X_tsNZE_XLroTHthNR7yH6_OlTAnsnz-MeQXULVlayK1hAOBpoz9Ei1-DDKQlShh3Jt65Zss3zE_Fr4SSDX3nVVzwYwzSIAxgdo6g8ohIbPGTwx9bKK0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (690, 22, 'https://1.bp.blogspot.com/Ja79LmbsgzN9z5NzoR0A2xr53obcFsItAJBxK3lm7XJapKUSIdEpUSUzPy6mDoNAtObPBo22fsdECHQbteEEv-XOPwkuTPBl6lBlPhvnmX2ZqMpU2Pk1Ns2_hfIdJ_GriOG9ey2DZH8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (691, 22, 'https://1.bp.blogspot.com/7ORLEjLiIPheXAQSTCcjLDpkNpRXvPx2VGczY8q4eco7huSDf2GMiRcCcKcH66MK4fS9CjVZZGxSyxm64s1VsD3tl7a7cnpet5hyUBRxCKAQF3c-NnHbENNq-QQRaGRxkEk3hYl6H28=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (692, 22, 'https://1.bp.blogspot.com/kRC6NQ0mBxubMdtbDMXHyL3eStQ1nLNE7o8D3FNOXHJyo4XGZ_-f1lMdR3ZuWWeuGjAnzWwxbFRt-0Kdv4D8i6ETIZQ2CtylqMq4Mhnaz9Wi0bvzfB8zW9qFaWovbWwN4CbR56VGkNE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (693, 22, 'https://1.bp.blogspot.com/mAZkwmiNUrCgKT17kCXZe1SJTJU3HCHULf3sO31S8COMVchwR_C_ZYMA1Zb3UkM5mIvrVGv0QvZkEINlb_ujyeWSGEY-k-MhcaDfFl80RtbwCArA8fRvt3mq5rA50qQJoUwV_-9xstc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (694, 22, 'https://1.bp.blogspot.com/bCPRmMj55-ENU9aOpe7pQQsowLIHtDSQQAFk36pBucbWqfWebPuJk5yS-EAktrRKmSI3b2hTy3ROimqjU6FpNXz0lxC9XJ2F_QfaGg7OowQGztybuNqJFImQtjH_VNVNzUW00XJD3qs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (695, 22, 'https://1.bp.blogspot.com/U6fOqJqZwsW4LhKDM17o3HkuhhyQr9rbYBg9tqyzzPcC4v5i8FkNQJNyxNXcDI70PFmHJqT4X0797NnPXRhSwRqYMXgN9mfbRFEfEfoN3Ajxb1xSt3zcW34jnadESJTs81FnJ7KNpvA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (696, 22, 'https://1.bp.blogspot.com/SjDI51JCluRYadr3hMrJH9lwxo3xJj8JSLpW82JAfQ1rLapDSFYG23L8flCvP-CNnFbab1_bYisbSpwDkUeg1EaCiZRj8fnUCeAPld4jVRT1HKuMf49iejItEg08uVCe0-N02q6IhHo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (697, 22, 'https://1.bp.blogspot.com/FgXbS7jgv6waS6R45U8hecAcbi-tF8jVKNJw9_vEib9dAo5fNUApDMPkJtD9TuWj-zJAUXJ7g-HF3DME6M0af3fSQzip4-6v0QS1JNdy3ARAi6_sthnYhKu5V-0d0J88tbhu3bV2fK0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (698, 22, 'https://1.bp.blogspot.com/Kp2Mx6sJeaLMxL7MrPVjsVre6qEX-QgVOeAI5AKho2AbuZ4QK701m7QUWHrzHva9ydWbuBVMb9qcvNPPJK6ZC1_F05_5489Z4HX-5Z9-_bvxxn8k8fmE6nAtTYnRMFuQQxMK3qOKGGk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (699, 22, 'https://1.bp.blogspot.com/0oZu4h__G5IKzKoxN1GcnC5z8LfbRsWB84ugaRnhBU5qU4RVDcuuSPHrhHuCX-jz1x8NP1TGjmfSgBvVKpGyQvGd94uJT6F_XmjckYdtiNgx6sbksVMu_ohAwdE9IHF1TlfrOY3S2CE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (700, 22, 'https://1.bp.blogspot.com/aXJl5hJSkDyYhDAKHqupkB9PP327uK8qrm9jRadYpQ10vZ3JDmDZxBw2EOap1akutWjhqqM6kldQvG_GZDjC3kTJl13mwaMKO_ifVw93S_rNu_UrlewAb4OyEY80-3WKyglq8zl418g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (701, 22, 'https://1.bp.blogspot.com/jUkgaBgtwjzOFgJuEjpTXUOCCIBi3BCYRVPEete4VkiC3aNKKKMUJvuD_olgN5XphIwTnNLNhfGAK5TDtBRHQ0fcHp1N2lNJSlTwCyzE3xRh2gyBMAgHyU3KVHyunsmy82pPmZ1t4_w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (702, 22, 'https://1.bp.blogspot.com/UshW6jSdTK4Y2Nh0U0VviAbYkmD_FqPjthATYnwCSh4R3i_CD_x2bTBuiWNGcznqqe9ZaA8gmaqYs63X18GY_uT2k7u-wO4h7QLqJwHyGYd8QxrOhOHUKQQUs4Xan3x4XFh4MSaXSpU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (703, 22, 'https://1.bp.blogspot.com/SkNpdeH8DcTxVvQ_5OVMfs7diATWJbQqNxca0BFHeIGeVwIrVZK3Ptb0gYfhN3yEGXoEYTujGi0GuXqa-Qithp0uqUjb79H-o8oS3j2Ak1JT64IZ9IHpMDTEOPoFMvy6owwvCzJCs3M=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (704, 22, 'https://1.bp.blogspot.com/xplGjT67RfcUX74CZJYI4JQF2E1yM0nyxySn5np25xIYNn5XFBwd1lCPlRCvNpaTVEUmwefWcPk47ucpkxH9H3rLM6GU32zxBM5W_q2ypPYO9rveYQIm2e3FulqgFYBeG0k8Ga2fEfM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (705, 22, 'https://1.bp.blogspot.com/YwqosBDyi1Dx_81kocpNhdKIAPmU4zrFBPBE9WxR4ykOc6K4RLyQgsSt76KDzE0MGpt3Z3Mi8C-QGgBYlITLQTaKBtphLDJEM9dzuDNx4LVmXWZ3ELuLa_1MlW10vvo6FMoUd-kdRNU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (706, 22, 'https://1.bp.blogspot.com/Cf0ZJR2VlJVlNv6YYWlgkc_6Y9xRO8Xl8j29HZsc1nYnuJoexFbLcm0aGesnqdDHkoL29VkdGELXy7yuUqp2GoesPVv2EYn4g9hMjz7fVrZSanpSJpf-hGnzy2wjsXLKlhSkSPMCQmA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (707, 22, 'https://1.bp.blogspot.com/C9Xpv1Cgy9QP5VUdKF96wHgLl4-zQD0MrTKhs7GWzQg5zyYdu4wPAiXGtCHNVNXfDT9XIkqyLIdwqlpPAX2T6z6lx1n0HUvzBs-hhjAsJUMtnQz-WT4EqxNmNPy4Qj21qkOsHcJTeQI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (708, 22, 'https://1.bp.blogspot.com/oqEFkF4hkmHHnco2AYfdr6LwqQXPFJ5Zde4nm81xCJ01nxQSasI40ypA3tuy39gudujSqfRrVKNtwmWvH4RZVDuA6Wugr9rG0P0rljoKOPbKP20znSFQt06qMLJkp2UE91WcD_C74zU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (709, 22, 'https://1.bp.blogspot.com/bpCeSW7hzMjnZzXCe-MnA0HLhtXkLlwk_aii_LCsQ4kVMdSkW9lE1Y_p7zo7I44pXb6FXB4ZcMgVoaarU-dFwnPkhQqV3n2SSO9RMOZkCuNHybJ7Hw1pS9g4MK1jOPOOVb2zb-LD5Ms=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (710, 22, 'https://1.bp.blogspot.com/nX355qFEP3MERjOaZYuTrhqEtZnbm1hO9TkGHOg1DLDn4UNAzYCMhIV9djANCHQvO510vNhF5pTP9THeOPa6ohLO9qpSNjO-FvUj6IRMBd3EjP93zIKvdDSEotcSW5bILL3bByu7KVo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (711, 22, 'https://1.bp.blogspot.com/jbWUXwbK1cM9GxLW6gEvQPxdJlpHkN0dOnXCWPSyA9_em6xWrld0Pk-aUDdhwR7NY4Fxkvs2LQZWO94UQ4gL5PIpnRLysIUvjBht4yLDCUIbu2RZvGQxo9xSzp7pMnZAprPE2JXB61g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (712, 22, 'https://1.bp.blogspot.com/Y6tQwEAt6hy4aN1O6kfRQ3VNSPpi4taOJbQy6TS3jHRymWVGWOCqmWnQXsMZUgjLiS8YJSotuU0UI7Joy4VCZM7k-hD7MYN1HtDfqsh6QsT9VvbUt_a_-sqA_ir4d_wUO8yQxHE0AHs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (713, 22, 'https://1.bp.blogspot.com/wPRn60sBErAjWs2pE2xkJQPn7FOe2E3pyt9jp45tsF0o9w-t5qIi_2xMQ3CQEg7rIsr_8hsobjEzhOGo9dSqXUQflw6STyZ_3GM69pcj_a5T6aepA26FsXaXanoCGcK7XhgaVsFvwCE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (714, 22, 'https://1.bp.blogspot.com/yOO7qxNzqiFllwbT9iBWUFo2eY2NQAkxDTReODaBsdKe737bE_FFJS2eaGpF6vHZef0oLPmEdLz1GkbeQ1g3N09pH6cR9MPQeSQPC06KjSK5cOz7z2SagcMueYR_L_WIvN-mloTS7mY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (715, 22, 'https://1.bp.blogspot.com/Bpo0fgWvnZBgpreOapbELz4VvQeISRkQhZdSkMA_hxJ8kTYC924MKi6eXsYj7Ed_5um75l7CdvJLBITdHOW1LMD0O8SnDSR7mZ5FNU2pyvi4ENKgbpdbpXw3wC-fXBy5rPhnbnXuaYE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (716, 22, 'https://1.bp.blogspot.com/XCwr6mswVCqAHxzwesqEAOux2nP5boeBBM9znOCVxNm_J3Y2pgx1KfbLM7hrJz7dthseyF7mBYO_pw0WFDgCSURonkFmSbL7Yq4GjueS4eEcx5JjymGTBuwSw9bntXd8JPcHe3Au9z0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (717, 22, 'https://1.bp.blogspot.com/BSmtvMtVNBcARR14l7gbbsObq1CfzRRXPUKB5JMbzI2wD1acacD_HdIksptIKQyIITxPtkc4wgkovTdmKwcQSXW-_Pqc8AmoF2vp4ssb6rBDOgMAHC_OQ-UXUAyuRT4mhwTIgyAMvNU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (718, 22, 'https://1.bp.blogspot.com/i29e_6M_V5cJAWCWaZ1WWdh8VTsrFr4osKU26dqOWnrR8kQrU9wkaVsfftpna_l8UJ3VEmPFzNanp8ZwDDmiwTyJOaHuBw-_yiftV7e_JnYIKQap3WtXcm7c7yML4IqdO8X-gjPXX3c=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (719, 22, 'https://1.bp.blogspot.com/5fjXmP-nDw3gxyZO6omlivcDWHhBcHl4bdXzsbzPnpTG5GQl3ZNV4vhmOoCWzTOQxLyYQ_uu3fYM1A1Xz6aOutkEiAOk5dAHI7YSjHUbvHP9ugaHid9nPeAYKIKpiQTdIJlCv5DAZR4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (720, 22, 'https://1.bp.blogspot.com/xnos-p3FTWPOV_f5EhxCuhaXSckEzFssJVhxQkxJSfi0TJB0lvEMW7ynQU6rt7mdtGj7XKMFTvgQBOix6_f4sbUDSMU3m5YUjl7aLSugc6tLcFNPdkki3AoW72qoqj8K-8pKXg7U5ac=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (721, 22, 'https://1.bp.blogspot.com/iSwAAs4QkuPTc_1hg561AcZzCbmUcagLo0ikyYdM-zps_Epz3aj5ivgaczxJbdPVMEeLFqQKSmW1IG2LjrfbKv0K-fvv6AUWWxn2M4UwGNJJtj1JbRge4fmrMlbJd2m0z0p38jNQF-w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (722, 22, 'https://1.bp.blogspot.com/Zqinxar5h0Xy-FTWPSfTf0TucL292jrXjoSOGrrMik927AuPb6Yr4YmXCuO9GHjcNXIq1TPABb1Pt8Dxj5MKXttMDqpY1Y6SNQtQ4PvrjjblFR0JvGmfcrr9D0HeFTeCfy8v4pQ69Zg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (723, 22, 'https://1.bp.blogspot.com/GhMfjn7t97ShmdQXvTEXvawtEdZdQ0paLR8F-lMu-2vuMCAYqowJ_cmUeDXuSWoN52WPV5EoHxWgU88TQBtNQKMQkosBj9RsdQ2jFXpwxRtIQ833s6HiK9YtlKhYeez2vM8P7SQyCTI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (724, 22, 'https://1.bp.blogspot.com/oq-HaXFMyrEqz3bzJzFS4gsv4t9i_3Dc2GpjF8uyIVpMsSKsjRD459ByyhzQqLY__cAMvAO5jt3PX73PP4uFaAfGc171Yn27Jd0fBvslI5lIwHMTqQKNMEQE9oJjDSiUQt1hM_6A1T4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (725, 22, 'https://1.bp.blogspot.com/JMjuQrtLKKZdTX7UZ1_fcbiGE9lCirG8GYbugjgV9wDkQ8tkaol6l4HaZNLw576Pwr5tGGR3F8R_1ov_rtn7pkMNSBjnm-yTeYOSwBp7cbyVS06S8rVJ7DkCMR42lrehQDrJkxJYOlw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (726, 22, 'https://1.bp.blogspot.com/Ji7eZWld6I96eI2fPKq-VISfv0vPBaJoDILDTd453n5ufXFkCXZnDmg4Pm-mTmQNFbcx4ehMnI3kNXFC2WGP0dm8DTYluk29qmoL8vo2aSEJUmk-pLPfCZJ1G1U4m7yb_9QQqxjBTSA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (727, 22, 'https://1.bp.blogspot.com/XFNX9xBc9TZ0j0-jw-ZriWs13WMaDH0eGRJhgG1ZZp9CfTYPVINUWi-g4JxRk-cWfEXZapYwVrjCfRbu8bycoGzVDlHM88OYc-t7FW-OUnZx545xVxXXYFbJYTRS_K3RSRVDd8Co9RQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (728, 22, 'https://1.bp.blogspot.com/scFt1AQoK-wjcoF96pjsP1YuyBgGmzT3v0CbLMxR_xfB2nJEk4uV3-l0bauZdknu2lqvXarTUU_j-VQYs6FAzplLSQKv9zge2PfOLEw-PxIpbS0B22TNb42a1BXMGSZG0DDpi_bjXv0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (729, 22, 'https://1.bp.blogspot.com/rrD9xLDWQuxTGowh1hnKPWc3SzOKLkwlv_FSqaBsSEhs7hL3IC0NG5kiUGhZBZFVsSs1DsZ4gDLSKoSBIOWT8XkA5JdCWiyf3fVPDw9BCLtWbWtas8gtVr86mB36ryUuszgodq4sGuU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (730, 22, 'https://1.bp.blogspot.com/hCNwvgNmhqst35yoElcmx4pDAWRZg46h3ACPcZsxkZTISVYroCi3eyN3JVwTrPnB9GdB19_t8O5BWREd2Nm2Sa7txQ7gJP0pyL_oSvyobMN0cWXEJ39DIT7k-TEwBpoMJx47DWl_IIM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (731, 22, 'https://1.bp.blogspot.com/qfiiryHJL1U4RLq0GEEBvNxcc47Fx4IDuOiLbGQZgMd07_18nXyCd3UqZjD7UfsQ00l3oZibS_vT9dF2q8gzyIy1qGwDxmhO-Gh-M1DvBUTdSEGQ1bbkymsyse5Ib0oNLjzZEGcpyf8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (732, 22, 'https://1.bp.blogspot.com/A1k5TmY6CV2Y5XD2DXQ72rcJaJ_sOLyQqPLxUXft-IFpkisFKuK45lvUNcluLmovHiYAEwF3JhhW3Qteq_-CFFXAx6Y3QM_X1DY35yLd4Zo5NKebTEBaWEzM31uaBba9Nkp_U4eqt5E=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (733, 22, 'https://1.bp.blogspot.com/o5o09IH9MkEAICbGSGKWRnrClLBOk94YqkKVTzoMi6699bIHepWjm35PBA2kPLhAflj7fndfIYc3nlu9VdhBkk46oKBdaYs4BdEv-I44_KW_1kWZCQm6y8xr_lY4BDKX0Ivl197fpHs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (734, 22, 'https://1.bp.blogspot.com/Y3WQm42Deejsg8vokvizCmzGpNKXARwPGyXJ7y7TLCGLQIECp5BRSZuSXK0HzdFz3Lcg7-LnqzcvIeSACBh1iYql_sjW7X5X1nzB1v-eqjK1a37wlFAlzb6UqErEHLYBmqsvg0_8nd4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (735, 22, 'https://1.bp.blogspot.com/ibSCCzNxAIOtc0Wg0yNbGzOLpuyJfTvP3P9iRsq2tEMPmblJoK91Apjr1wZ5AStTRNG1xpoLOtRCXH1ALOHQFrtPPeFTKe8aikualIYf4I4H1ESw_v_GJoMaorO0kQwv2oJHXZf6CM4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (736, 22, 'https://1.bp.blogspot.com/8f4o1SBctftqcypHZIYI2whDs2tGeFNou6joCCR4fCr70tOTnzey22wS9ThPqBmbFqPA0epZghQAhBlQitl9tAFyTXl_FBsDKRuv_rwvB18jP50CCMxRH2bkef6j1REDkzUArWaPuXo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (737, 22, 'https://1.bp.blogspot.com/GTrY0aJS3FmWTNDvhYGPkK8wdlG0Ft6ZDPp6bf67VdyikuLaZzRjd-jGJ3mfkBrpGuzOVpPxJ9QoCidqBDhfs7qCO4_J_ANWce6Ru5bdUfCsg8QkdirJpQssnHcWyKoK1vXkXbh-5CA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (738, 22, 'https://1.bp.blogspot.com/nLk4KacNBaBhsFcuXthxAD8GXJ2wfzrvB5e7Qc8MS1_EIV1HgdK5qLN1P1JBL--KsgfvxvvdkI300yNQg04cG-crr_fiYxZlkVdZV3T26RTUAMBadfMqqnLVpjpFFJtABrKnku2YQlk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (739, 23, 'https://1.bp.blogspot.com/AV6Mk-uxJbj3ZECrcmiLCqJXieBpQGkJN7G1XhmThGrMUlLTJ6d_dvHgq_SwVMMmdLG3qTulnm3SHnpZSqDK6lq1fTyEIq-Vl7MNpqO7AUx8aNgltb789yeSJK8wx5Du9MQJMRbpFxU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (740, 23, 'https://1.bp.blogspot.com/uO5I6HXbaZPnVnWM_-TtwNKiujchhggoM_lk7CCuzS4Igl51BUQCbCsUPzXoH-ofHJ_YO4GLvWVJxsIwzbcA9Y_t5nSMJxYTsQhSCDbPdn07rsPizPvtwhsfceVH7Vd4NGm5WTkegso=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (741, 23, 'https://1.bp.blogspot.com/Cn7xFdXCR7i6ECV3Dlk2BSTHVtorDUt24uNbg3zHu9NTltLhJgAdrskYxzLa26Q-Hqtzt5bEzN-QAB7BXhzUyURs_rp1x-lm1LK0mJKvXcdfR4nhJBYopAlAp5NO-YDznGUYKy6U-Vw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (742, 23, 'https://1.bp.blogspot.com/_FPnXk4iCn3R_JmCVbIZ2AZL1ihzvjp2DgYoEbqQ4AzllLGQk-OXfKo8esqdVKWcsz0-eY7C2Hr4UEl-5kVFBBsqWaMvB-q9gvsTWUrc_UlxkBhx7h1AklIQYBqaf5K7b8Tpi0jan2g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (743, 23, 'https://1.bp.blogspot.com/Lc_HxuY7dptEn2ixPMi2-qpD0waJ1h4QpvEDkUUesVKlRSsjVxfuH8ncvj62T2w_UAiJrUN9lkRLwAjuGrQft2WnMa1v5RUCRk3ixO9eB-9Gp6j5s8YOpunkG8ZL___TIOQJWezrl5c=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (744, 23, 'https://1.bp.blogspot.com/jwnUI68RBUX7FH8PaaEXrbZkW5QE3QiUb31Sw3d1MgoclJNWqRVFNjbm4lDms5HBkGihAgZLgs7_wn9aDB5urEBBMe6xC1SILXePcdIEbNIQMhNdNH4lJ-tQumMsQG99XVmB0pty9mk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (745, 23, 'https://1.bp.blogspot.com/d-aMPDVq1KlnAuB2s4NjiJ_6_1-RRD3B86yEZkcuyXhZ6_IPSNCDgNqqlBvCcHr1m1Ti1LucmB3EP1BnOWGV6T1HceVH8E2OfZhA67XPe8_K10sM3iI4GwyxYxVv3HQNnQxJMAZS7Ds=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (746, 23, 'https://1.bp.blogspot.com/bGfgCUVuCGEw5Q1kk0sDLhhjalzaSMVSozxa-llxOksrpsvF6iYhVB0yeYG93QfliLVnf2hqSFizDonmpHStTNprJGTApL0xwL5HE3UuX5UkZhvSo4WLzG-11yQFk-yR2NygISSjlLM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (747, 23, 'https://1.bp.blogspot.com/rGHhRefWWKt5tV1GNWVhcvLjCltfUUTyD-7h_dT1LXWt8eCXG0eG1VRtQX0_pcRfTzV9FfejisAwZnNzUpnuAQ3h40ObXpeyNOqdFNsobES61tVj5a_zHpkUE9LvlpJvy7ACqdAKWPY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (748, 23, 'https://1.bp.blogspot.com/LjlX2rUSKC2MC7MtdD8uZ4z2tb_Y0z16VqGMxG3JYw8_DGcQjslzSt13JQ2tcXBJBSx0ZDyMsTW1WB-oUOH_cRVD6vuEDovsFFQB9E3TfnP_3CVaHa-skHQ2zhpnAf6IweszR3bSFW0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (749, 23, 'https://1.bp.blogspot.com/ErbkkKEbLaudIp4479j6FyaUhY8DTa2jal_kOSafwllE31hpwijxRkGhvxXqBt5B-MMVseAVgLaonzC0JSiR6M_sdwYo-ZdFF3I7n7ieZPaV8oWt0LPTbxECqavLjKt_Tc4e-0-Mo-I=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (750, 23, 'https://1.bp.blogspot.com/dO11qDM3puOIPBGoC7PTCMfMAPKUC64bg20y6SdP8MteB6wVTezTrNhSOn51kBrK1qG37RDq5r8zZHPAYfgSvRZe5so4SvUSQj5txyOx-1IF6gg0m2ZJdaKxHQagqQDf2xGVQN9PiGs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (751, 23, 'https://1.bp.blogspot.com/n1j8P9HQ4-8jBjydJOVwu4sC3jAuDj8peNwdnbHf8BL-nxNEHBDsftbuGfqPg4tNRgcKTvuPtxH8NQiNqG9DVEiA3Ryrw3RkxazqVxrskfmhHeVmKa9uYB-FfGnGDJgoBLLXeE5XEN0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (752, 23, 'https://1.bp.blogspot.com/kVZIrglX13fNn1oWs7JKwvvl7ajZkV2qYxX2VRFgngaEcdtIkHTxz1komihbA_HJ8s52G30Wxu6TOyoLOqBoZuNQMIvdDcUTiEWxoXBW0nyeyvkMLpMXRWwjAXypfpRU5UaFt8H1I34=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (753, 23, 'https://1.bp.blogspot.com/VRShk-NTU-dE6uJlqnKDqWSWHj62Xf3ikFPlZI4LzqmdnRMBMLgmkUVhjBGshepA3vvt6e1XkHcV_MXRLo5UGUC6kACXXDNm85fGqbrMGHrHmoi1HHAOaZ6UFC1Ig4OqgFp3zvZT_MI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (754, 23, 'https://1.bp.blogspot.com/2ksEH5bOZA9Atf2nO00u_aCKnN-8RRLW40QCqrwwmJ-HORKkPxjekXmX6gdqi_UZTd-NTVlhaQgmJtqQ8uaXIrLBoxEIss6olO93LX_secwBvbfBIClnNCRRQbB2AUWgJK8yz9RcCgE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (755, 23, 'https://1.bp.blogspot.com/AFz2WKlrzHWlU4cLEMqP7fOI26UDo2swDEZ5lslDInXeCV0mhOjf-35M13Nwm77zdjYwe_L-vkHLUsmBZQpyDjmx8K-H2oHWMzj_J2UhXPwU0AsK-dM8m226MQkFubEjlXzMh51ndVI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (756, 23, 'https://1.bp.blogspot.com/dMw8-Xz9hF6zOfvRd11oiWROZ1TGUUEwTqB5wL401MIGXidN3sU82iUzBnVZXSbj6wMAiTfxXnlbuxwF5uDj-5Fg-vovAM0DogwMDv6NG6A2m4xcYECXcDdic24FA6nJNIZcgq_njnM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (757, 23, 'https://1.bp.blogspot.com/SJ9wxw3IVIoH-pk1-J-5biuDvk-Ry03hDunKAiqF3TQmnEJ6CLXDLTAkLQT7i0uQK0nQHR4FlfqpW_tGoMGsP_s_Rchk2DN_RbsjVu6oD1pxCP-VqAGFlNFpAGkRNFh9TKTOdJXbf_o=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (758, 23, 'https://1.bp.blogspot.com/sEERa45_wlPCZah9XtiSYnyYtZ4wx_Zt-GEO13tojNebEWlwV6nwieiE2T8h0vaD-CHHb8h5C2sRn5ZO1ZohwXTtgJGBq_jxpkQsuFRzBlm_LX1lmexh1rHNVmqcnp8UINAei4dvd1s=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (759, 23, 'https://1.bp.blogspot.com/MrjbBhlxRaa7A7f71KHNgR1FLUsZeHKxt8QRE7jXUyCC7SmNwDwdih8F19LA21AtPI2oyPJCdB0wD_Ft3xv7W6Dr5rQq-2BvPLb7xWg1GVIdmtb6aDO4BJq8bHAhllbe6iEjE3IpgG4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (760, 23, 'https://1.bp.blogspot.com/pgzSHuNH8D0_ZyHXWzmdJS7w1bvCnuxTxvZVGv7yCEExbMBkIO7nR4wyBgpqSPZ6KG50rmThk0EP5zb6EaWXd8PSBNmvFlXSZvPY6-E80smPL2VeedSIbbdZJHMYVpCSDvl2mlmT8SM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (761, 23, 'https://1.bp.blogspot.com/yVEjXyBLAeVHZRMHUazG2uG_XLU6WPgiHTlRPbkxmXV-mG-pA0YYzDyLi7HMYv4KYTEAL1HlrYLnDZs1p_Rztg_6S6ZiOimtZAGcBquTa8LiQQeQgdVwE2r_7wnVxk3PNLgKx9mZB0s=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (762, 23, 'https://1.bp.blogspot.com/PAwn49DIy6WEu5JI9Wm39MLN5R8gcLKAzCgWc1rytttwjJaMIwhH_siNw6UHV_99KAuMZb1ld2xQ5l2e5ib0Ehx5xPaxOaCDlPPWvH4dgs5hacf9RgnfvANbOwn_RLpbEyWnlOsQ7yM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (763, 23, 'https://1.bp.blogspot.com/bNruwtynl4YVQJeWW2sfTyqsXqMys9uTDY0dzY2CqmJKRtF2UJn3vTXXh9E3l650ZsWckc3aZjrT9iUbtlH-3CcdycWcDlF8tP4F04kwdaBBdyV_zuDQb9WhRwMSIFWPwdkwXKCgJq4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (764, 23, 'https://1.bp.blogspot.com/LedQqiIrrbiwMh4e9aSQrZA9CCCGkO6IiAtRkhT5iUszKnqz4c-Z5jzjyGepqQpIlFZ9juwlyZ5rFjf72u8ypL5HNZ8sFsoikDlcvLvKQ85xHSev2ThzS8CDF_2wQpM1LSvwvZ5565c=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (765, 23, 'https://1.bp.blogspot.com/dEkNngjdojx4y0tLFtF0AC8E7y6Wgmi3lYeqYgmcb2Z7jSjYum4r_C-xn2mAKsv1DC4FF_pt1kK2PqsmLLe6i82aTmk87t0BF61gQ9lu3TpKFCqYd07d3WQiabyySE-hrHp_tBxBEig=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (766, 23, 'https://1.bp.blogspot.com/VNr90qFK2hE6EpjHbtvv8Tp1GgqJb_XB247uJ-B8Q4f5xVLxSg0x4q9tYLO8BiGVawgclx4OwAWzLp-2-p49a9oTCzMlZ3xNJd5aopdnte1zKIB3G8VmwMSbQ0MZkl1ZeJCCEg83f44=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (767, 23, 'https://1.bp.blogspot.com/rfMQb94CE4ymn8LWnHBOSulVxxoySBZgb4HtXy1XD6THj3Qqz5NoxiHXsRwdBADkrPfvf0bQOVQuc2d_AkJzyzIa4E9hI9qPYv4onJ_MNKJHPZ8ANC3ezQlHOWAd1_Ff_sIeGYJpNC0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (768, 23, 'https://1.bp.blogspot.com/WxTfOFpuEUUhSgFpdvV4AfvBXkZaJ-icGrzR5NkROhzEqfjEvNQQWd7HC9kc_nvfKos1dTNgCwowf59_siCCncZ7jpZSk4eo1J3tZDRYZ0JDSt3dSmN-sS01B4Gx-3MyrQA-BIS2kFs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (769, 23, 'https://1.bp.blogspot.com/YvxYt5AkrW5kCocI8cDkTab7KHiD8GzuDV6MZaVXRvX8B2qIT_VszOpkFEIW6GIKsHau0wskYshnqIYnRlnvm2orY9PYhwG58jXOOWnrsGknAZJu1Ym0dhKqrWl0GBKZeG3fJFcvV3U=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (770, 23, 'https://1.bp.blogspot.com/veqklXAO_1kVIPc9a-rKj0u6MMyvKmHVhxoFJZb0tXVy_0FqQzpDLxKPLnZbcehriatKS-vBm2IxWsE3SL0TCrOMCAZ78IOuoEf1yv3w8-paSfzZFK3ZWSlDYwF1oTa6IkOQb2t2llA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (771, 23, 'https://1.bp.blogspot.com/5T1gQFG032yBht2fWZ_toSZG0n3fXXbScwFzXIfe0F1EawCZF7K0tr5CIHupyrqYrBd2NoQ0p4k_WiY3yEW-IlhgJBsTZgGu62FfxXPiM1xEdU-BbXTuCwnXdJrq79eNyG8fXeMl0aE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (772, 23, 'https://1.bp.blogspot.com/b0SIdBChDvN33ePN0KLhS_xRSpJI37cg0z7vCSoyuaCsay87Gc7KwTEYcwsNY14ljjH6hHLan34dZrY-9pkowCF8y6iTGfBZFyu0a5UG_2ehIj-sFOUh8ivemqVwkDiB1INTYAZXmUE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (773, 23, 'https://1.bp.blogspot.com/wsMRVebfqITpDzqnP9tXgjHS7oiIN8jY0rFxaMprF6CH_tdg2Yf4atFP41TEgd0xnx7zBz6OhwpLi5hbt5fGBWsfPscVv2tATBbBaDPCXrkJVtL8UHSdzg3-h4R_AO6ECPpzuJ3ozOs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (774, 23, 'https://1.bp.blogspot.com/2CWtyI6EVBeEh07guKvleAe5qq_3xcuBthUjShIOFuZhmn_tg-9mAlAIDcAVhcs0guBu827gqL3MO3bBEMiLKxLNodCH-vFI7sS7VmgLEdSaQoe6JHs97TuE4S_Gtjc8VyVrFGgtpD4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (775, 23, 'https://1.bp.blogspot.com/QSKjApnXa_m54oRWeaxnyPu1urnl4jgg3NYS2QR1-dnoNENQ2hoLtWlVucrcR6ZFwJHb1NR4iK7Owww0QXn4xOwa3yhpPtQkq0e1eBHi6zc2TqYg3bAa2_s_RjhSOT2Hfq7IHG54qrA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (776, 23, 'https://1.bp.blogspot.com/rjJ9y5J8ylM4k8QkoN8gLVXMJB-7QxDQAIzlMfDk0AhdESzTDBkhdkAN16BWxVFen8rAvlW6oZomRlJIQK73GwhGEpFRxt4up_lxGUu5G6iqqVgjWSwXSnJSvUVj6mkhS3V0eoYo5RQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (777, 23, 'https://1.bp.blogspot.com/0QqiDoYs1bP-9gsbWQewIpLRv8imGpukA4flH3xqAvfcgaTNqt_ufUIW1FIQ1vD-bkSislvU5oCSdthHfVaaNb3aNNqFfK8H-U5WPgAcn-JM2uLaSpYZCGFNcZsinjMbrvJiIzW52k0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (778, 23, 'https://1.bp.blogspot.com/41e0iXfwjxqbvVuWdnRw6rSX6F4S4OaviO3ufuC3_jOuYAminwf46YM11LbhUfhjMuVY62AQjo4v8bf5RfLgA0NsYYs0RpWuxmfwesdv8QjR5PEEjl5KX1Atnhb5Ime7b9LSg8gdKPs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (779, 23, 'https://1.bp.blogspot.com/vTScQfW_ohTHWtiwYK9YNtwK1vmMwH-glmHb6K8f5_FEvb7GjDcGIpGB9h7geCnZprLVq4moNJtGVG6Kk8Ygax0hAlbHzqD0auGqfaT2L1xoVasvS1arj4LcAgE97Lg2Ygnyz_h5yOg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (780, 23, 'https://1.bp.blogspot.com/bTtMHBfhU0fLMO5uCTClXwDk9GdHWZ8soyNCuCApDqUFlFzaW9H1nidTT-dGDfkqd0eKe6P6X6h8RoXXbL6jp5Bc4gtHqwvAzkIR0AGearvIS1tLPm8QjZtM3Pj49EYAbMCyzlTQ8vs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (781, 23, 'https://1.bp.blogspot.com/ePVy3eFMt0pLDP1aeA-3-E8d5uV85wqPZof4dNrjoJW2qSGs75DDLlkVG0-STWFrimBkHqNh0Aq3w3sPs7QIoUBJfnPo6uSBvPq06Q8--E5LsZM1Sw_PjDIYCf0bW2RthPnnML_eA3U=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (782, 23, 'https://1.bp.blogspot.com/fQUFiva9anGHkHaRkNDS-fI2aVyqEVyMREkEGmrAXDmwCYfw1UBxnfn169z-INDS6HSh0waB17zf-lmvxKkr4vyuRcI_kdIke8i695k6sCvqzs33F5CmkLXTwurbE9Urb2IHF-K52D8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (783, 23, 'https://1.bp.blogspot.com/y3lNG2VcyVSQRaFdcs4t-nZUPdNa8GMj_qMQgDUlK4Nu13P9YuJ-I9k0y8YL_ZJFo0jzh9MurIDm_pARIgBdZzj74ykgNQm-SIxAeGS_kD7-c4QM0aqfXnI443lwlrqhdMLYZQvYlZ8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (784, 23, 'https://1.bp.blogspot.com/I9oyK2E9JxtmW5F47V-dxzgMKRxsh5GLjavbqbmetTIoq6jMyxK84I5QEoTInzJSgniG4fTJPMPT_73CB6cGbiD2QfY1i4Q083KqAaFEWEeZWJ9LEq3RZikutfxSw_HDXgpF_ZMkn1I=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (785, 23, 'https://1.bp.blogspot.com/LPInt-xugoGEuOspoP6jc9nxB1-_mU59NXpPHc5X0HDDSC8Gz1si0CofFpZk00mQ12ORyPLJWGvNMimUei6URW2gkFjyzz5vLOZNFo8scYzjoLy7ExcvO_cBIV6rvEyk71SnYCLjNl4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (786, 23, 'https://1.bp.blogspot.com/ZlpIIMmvK5sr4wO7FnRVtEaWcnvGmML9u7YUKubQQUqcxHodjbMorAO-s92a3sXsH0-IPao2Wva8n9H0si8aCZYKdPrFQb2Rth5zjys3VAW1PKqtN7fAMZGE9RFFoCpmBMbIzweU2aw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (787, 23, 'https://1.bp.blogspot.com/SGvAF3Km-LjYMrV7u_VQZbJqwB5NJiKww8Afi_yGuqzn1dR6iDU3EWaaACXiqcat0U8Sxq2OJk-vK9j_aCGWaIoD5hPMefZlgYDpQgqZnet1NYFrkdmmOET1p80vdS0pYJgmwWA1KtM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (788, 23, 'https://1.bp.blogspot.com/LuaK4vCBEeF8uIjR0Dr8KdY4wsJxybaz6FNVIRBRpJPIeJWKvqIQVAzUyT-QfSi2MRtv5jCcDUpIEn2CxDU4KxhLX_w5-qxj0vl6kzQV44Jt-GVwA11Ge87Ti3eJgMK61U6pED07HEM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (789, 23, 'https://1.bp.blogspot.com/usObbCrohEpImBpEdfk_zV__o0Z85m0udpchb5-rOjPBDojoOZF3G7BqzrRLZam751CHHVegQZ7gDF1RI_efTCzKWLePzjXd6XsLH2s4LVm1gX1gn2bqBQ0j4_oWGS29fMWbdIpCnEk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (790, 23, 'https://1.bp.blogspot.com/7H3-0q5HZJC-rzY8cxpD1O6wUe3VIFXjdi-e8K3MpEyQ994dN6-sNE3mSU6KqNzh4SFe1PhXiMT7-Is03Ii6RJ91u2oOmBVd3EMCRUbYG9W6Kpjtp7gnmqCM75a2LHOUdE1caGV3ih8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (791, 23, 'https://1.bp.blogspot.com/9uw6SSZ1r9iG_EnhbEFN1LF9lP_L2C5Aos4LZtERCzYe9-T8ya7SPlMva-q8r6zG2AkgTOMSxVEW3WutVKFg0ddl0YWL5oYK0yzy4Siy_X28_hZutsuH6tajCo7Pp-qluYisN8xH_Qk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (792, 23, 'https://1.bp.blogspot.com/1wV8TPyuJGMv9x1gvAKCszWJaBh9shZUjNgBBRmukAT2CeF5ZUZ9-7euIQhAF72q8CAIkUmpYHC3YS40Klh31jblSYyAslVyLIeRpkOZFkklNRgCflo-Zz4wtfLxPxoD3zTsFUjKLp0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (793, 23, 'https://1.bp.blogspot.com/jrXv2EfTKiJq8f5cSbTZY-zhzm2U8VaMU_ZNEaNCYZB9-YnX_45U9-HQYx3LkRtF8OMyLg548zVG7e6e_hwbzAIpkP5XFu49iKKbfY8Y51X3ht3PCgA_19tLVqzH7R2ws1CUrLSDX5w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (794, 23, 'https://1.bp.blogspot.com/0W66gTDyT2nGuSWEkiREMJ1OX0AO_XTed8Yx6heRmmhZ3cCXFqbgNo6_18IC-DSK5JZG76AXYT7uN6uu0pr7y9x3YpcAEaYYfPOwTmygmbR0SBAbM1Q9HwLgUodmY7PWQMjBbtoEzEU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (795, 23, 'https://1.bp.blogspot.com/_XevFtshUwjc2rcyQZTfHkm0CiAbejj_x0Fc44WTPNPBPEGIpiTuBPKxhYoSgpy0UUCmPmlJgTMTnqx3kw0kh094enOS6zVyg5rRiC8mr78miEpl_NQCU4KY-Y4ml4zQKEpErF3bHSc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (796, 23, 'https://1.bp.blogspot.com/5W8VMPNPrXy6PAPkNkKdpUs1l6-MY2F_9Fl_YifJEK3TBt6Bge41IkKK5IFAk31SHCxk3kw0meNhlkj6-mNIfQFZzQhf5VC_6QBNhl-E9e_hpBqbxoMbyPVCAajTPHnfEnt0JAYu6JQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (797, 23, 'https://1.bp.blogspot.com/C3O4S_EQbSy_pEi-KB2Fgw0WnU2S3dcgs75ECN9oBJFRpYk1XTpeGZcXQmJ5bGldlhQ4DrCH0uh970IoicURivnlTItGH2-LR4dRGLLBugkcuSMxiQFhYVJOkTwLbo5HVrXMy5vEexo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (798, 23, 'https://1.bp.blogspot.com/LAVZ1fLli9KSpdPtYDivNyVjGc_gGJ_OkgeJOXdoyHf8tOG6rEWfVPhqXyEeZjgU7-SruG4AMH3dQGAAM_5N4lI3Cvt4jQIWlSRbu5VrP7uA5amUEX6n1ge_Wm9fED6jJooHwC6uZQk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (799, 23, 'https://1.bp.blogspot.com/SmZ7-aVIvR58YRP2g0XlOXtOylBx3ERRMObmVU05oL7gdCDqL00mAbbVIX-C5fD9kbxPaz1UPBaZ5F4jhh1vi8qUpZLcFskkStrEoEezw3r67guKv1kThWhVRWF7vSNO0w4pUGTjicg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (800, 24, 'https://1.bp.blogspot.com/3-2YurRwTzCr6kBuPliYtZpT51qneP5GCJXEDH6j-pjYsbP4A4DMMAxOoZ9yBfHszb8NfLtJ1fAfZXYgahMK4cZk8zES2J48UKNKVOKwWLDAq099JeYJokWP4NVJNe4YA849WHfGiwY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (801, 24, 'https://1.bp.blogspot.com/KzNf6FJwtDmcRjmGgu1RgoTmA1P0qLQH1YE6Qm2lowILiY26n4iI4wz9d4CebVtwK5cdNsfMP6IbWNgBp2ocksyLlH2glO5v7ByGK-sLn6E58HRgmHVlEL5CX2sCX1h4DDE9FTwjvQI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (802, 24, 'https://1.bp.blogspot.com/mYlVuimevXGzoou0xYLIOKqLdHY9Gclh3usXqehgLq6vUmnNUmQazjZUr8hMhE7VUNWszv7FrW8wXz4W_VPDj2jXOspQdzbZQnbcW3cuRi7_y8Y5hpdQdR9EbPMKcEYiJq5DKCfVeGw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (803, 24, 'https://1.bp.blogspot.com/Bo_LXiDRA0t7mUo-rD3MhunS20PcYmDojElYI2nPWBw5-hdusvrnDO71TO_8G1ehalf-IXeXFhasnMw9Xt3r5CJV9bae0ECXakAR2dJKAzvRAC7PlgVqm2q05yjuuvZc97X5L91dpII=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (804, 24, 'https://1.bp.blogspot.com/QR7DAagDx63CsoWlPiedd5cFDXK5h8u5SQfpUOuslKXt6e_A34R9R2ymbBQp_eS27YrPvzh_mPotk3bJvnCUWMFr66yG85zQxoMNRaIv4lPLPtXyjTpGW6iZUHnb7lpXPdT9dA_M4lQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (805, 24, 'https://1.bp.blogspot.com/-NTha6NO1KR2pXMz2OD-RoBNBhBpPobRDHXo1zHDK0UE4m6DD5mc15p07JMdtkzU-0R2QrlPQUxNqZL3s-R7OLYwDbgPYqTbJjJXdw6TE-kYp4FJdPGfic1_sM-p7MMcdMDSNynx5r4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (806, 24, 'https://1.bp.blogspot.com/xUm_BJ__apu7q2G9w_qTZcT_oTpEZz9BLEP9p3majOc9fewI3NAVztH0vyfxDtLqSw8VA4d1LkZtodsQ_yed4Ja2M9LYwwCY-HWs7N4OrYS73hAH0L7jlkPjvSB8_yV3yvrR1wjxMeo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (807, 24, 'https://1.bp.blogspot.com/FC3ogYSIG9Qk76_QWeL5dh2id8ruv8540P54jwtC82CWyIIpNDxYde8njAEkLoTaJTMYAq0Cpp-qo9QATDei6MF4SiKFr516ANcG-x3zKCYLsVOny8rDuh5hgUCrMq0Ws44msKTHFjU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (808, 24, 'https://1.bp.blogspot.com/6zvC13MUNGL9ObC-hcyuDJO8Fhc2vWcM7WErD1ebVVnS8s8mo2iszwDhuA_IR_ZxgZCxn5QGeN3wCDWReyZ4oujrAcAJQMihw5C8PPm8N3JEvJ8T8jBCVnZGYp_atz4rVMoH8DUvYLI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (809, 24, 'https://1.bp.blogspot.com/yYb6NN07dY1nI2KQDn62Wyl_0YkP6apgU0Fg9i9qYKsoPJuQQpQcvRRITwDHcXUOCYizd6Mn0ZQgNjuG3Xq6k9gs2ryf0ssjwX1IO5-FnoMelmdTTu8SWcvVTEw39NSWPfXeM4Z2lEQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (810, 24, 'https://1.bp.blogspot.com/3X2wJtYhA_Jio_l-GU5t2r1HSnI3H1oLN31_h1TwjP8vspvkpm1K9-ZvKjGiG2fybv2TUreEHFjP4X3Mv9PewjYnYyXoAsyOMLTq2XRXSrpOLcOINwgy1BEbQWOVo6_VNiUVWewXBhw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (811, 24, 'https://1.bp.blogspot.com/EWQqJPYpINCm0RDgYmIcBZtvlp4loVAlOZ7oTlUTwfRHwHAYoJyVmLhNwxCotKmOAz9aaltsE1uBYv-8mXvIsiJ9PlZPisN1OMfnBl82CogoOw3riUL7_9QT-EMN6_kLzq1kjENLdUI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (812, 24, 'https://1.bp.blogspot.com/FKyIOq0C7zwB1ZFaDleSA7HLMlxSqS4BjLYsvWaNYzfqfCkVuLrNs7bZEFbjRFcaGbFoeOkWLn8jPkWwWbXyeTpOWiBw2a7n_YRRBHK5SqQRTBK-IR8e4N0MouLDpma387Vc0KHYEGI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (813, 24, 'https://1.bp.blogspot.com/vYLVSFFeaIeuVlMf-avXGCyawdFR7MOcUEOd1AtiY0XlArAyfGDs6x3uu2e94ROW0YGLH9zulK95onfqegvJJMHpRNBhS0D8G-qoUu4G9rdPXefK35Ma5k-r2OozRg6LD-DLEKlT1bY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (814, 24, 'https://1.bp.blogspot.com/lKAERPE6CzxzFMn4bvl3iMBElywqzMo8ydVd_a77Flxd6vNn2oYMzBV49kQ38Te36lrtf-9q8BtgDSCQk1ee8sw2eXMSmT4MlcBkkQxltadTuL-Scacml4jLu_98OXyQEaL7OOsaES0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (815, 24, 'https://1.bp.blogspot.com/JLM1P3L9ETLAcZn-RIZdcoqatdC1KtGqEiAqGNGUly7_0lMLqsCmMtRqCbRs3qoNE2XZcxQIODZzm9YYzRZoc5_OQz8znCwcf1r222I3ZG_1Wj-IL-_loayx3eoQxooMFVpUdoGSqdc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (816, 24, 'https://1.bp.blogspot.com/WE3c6HaiQa9uxtIOrrBKjRRH2YWOLaIvqBYZb6VDXIxPmcHjchgTIdFU7THYH-bnMOj4Ya-8hCnq-JRo3GKho3QqVBVX8U3ug_qSoLrKRL0sQzzHfJwvuAnU5jnjgmR4K-bj9yejrvc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (817, 24, 'https://1.bp.blogspot.com/ooxnfCWa_SeUA5foAD5j3QGMS2kP4FDl5yuCbDdaGcKeEVJ3RKcYbTcAInouEDFAwfX77NmkwNcedDCygYk5gah-DYqGgl4WHjQ9EWkd_bGu_T9KQjfbI2GzOr976j1k4dFkWH69EjM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (818, 24, 'https://1.bp.blogspot.com/5jmJmG_jx8bTMoGxe7pJPLJ9Xpj7Jr2AASiwfaWAmmIj31wm05BVuPEs1gJUMZw3guvoN3eiaJzS7vcvstIDOxjd9uUezfL_PFN-l_yrLStmnTUcjGevDT4NfNSi8W1XZbc_m0MXAjo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (819, 24, 'https://1.bp.blogspot.com/G5-WYIZh--_iyzv7C7FnjXf7maB3dDWbyhidZ96qW6biohwTjjthEHkmOShhFrzAchG0rFJCOBP2rBgdFFtR8u91qtqcC0jTJPspYF7P4LLt6KDvGumqTEoza7sX_8vOHOieVl4XyQ8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (820, 24, 'https://1.bp.blogspot.com/Z5z2Fil2W7v0b0T12BoEyTj3NqluKxl2fyYUhcFfoYgP1577T3qgVlP4qv_S7Y9jPDgZ3NU7Ttq-VPo1bAtkodmrID0lqxU3C7O9XzHAcZTS6GUSRRN_pImZIanXxZULWWTPrFrpD08=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (821, 24, 'https://1.bp.blogspot.com/4knRnSnwg8JJ7Bpm1bHSq6pWst5IfVcs0m3p2JPJZDS2zcRsptCqfCNJXGvKKCFNF92VYAfIU9q999sIfLP6JTzSdL_jMa9c1KfchkqhrDCGR7OF9WMDe8pFI4ZRg6mLQ9iU5V8hVP8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (822, 24, 'https://1.bp.blogspot.com/oMU-UErIxatWrkGxZ7Omy9X0J8u_f6HugLQHZQeJ5KzAGt2GjSliuhwPg_m4L1b_x65gYw_2unnqgGIY2e2t5-u4OJAYON7nCWGKjfZWJHBDx5ZPeul5D7J1q4edzcAtSAm_lhunQxY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (823, 24, 'https://1.bp.blogspot.com/D_qCzH6LvDHK_XKFCcr2anauO7PBjFyYNK7NzlYJXs658bpdIw3uTex7Iy_mumlC6FEOBzqk1w4LXjWZSGxnApxE9lw6SnH5PaPvohCzJKaDQ9azUtUX1q2aJ8buZOjAMgIeaAhsAPI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (824, 24, 'https://1.bp.blogspot.com/9n9erqju370ro4SrhVK1QqQCeH1I7keySEFYLI_AUfpwo8NgM84rl9VmB7t9MEQAEBUV-O3uspy5BfobkwtCN1tXhEojZnB3QjPGcmGukglQjNUnRtCOUM_4RJr_eI6-UXMQyS2ZA1c=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (825, 24, 'https://1.bp.blogspot.com/1_5yxrByfIJLsp-1oAd0_HLoTTyzXMlVCc6Guj9eHQeFd8Z4RctDEFV1tDUrSzRJwrvpGoBPJoxgnWHhWJn4w5ZF_Nkk2u6hsmXrZJirkJTDXB_19_Uk32dzaP-xmTmqaHbUYemKj24=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (826, 24, 'https://1.bp.blogspot.com/HHrQDFzqIZyHINdu5kaFJ39vmHHnDdBXy_Z3qlaa77sTFbZkt1WCP8WaxSaEhtC9Dj3reZc9hMLWSGCj15QQxd301AU0OPprFJYmz9k3HO1j4Ylqj9IBaQuqC0YyFaf6zXCtcoFzID8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (827, 24, 'https://1.bp.blogspot.com/IZRLZsxGydT4XhCVRoiQhsDzcKWRL5zM9NjwaQTXXaAkTbJN1RlMmfIMG8DhhXhITzAGjLPO3_Hbja0KW80YDrUVJsaKV223SvQhP2qrVK7bBs-Io1run_wVeeegK9afQ1fJGKaiJ34=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (828, 24, 'https://1.bp.blogspot.com/bHtoLyz_V1tfF0e86We9dBy5JA294nwLlC3LXX1dPTApSZ2VFZUfF3CqQtCdMlo6G2zs71KAqbKKyjx4l-fzOZaU6iwq4XpeAy64glyj3fNK6qkD2Jj83o4c3Asn1wzcxVLSIX1CZMw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (829, 24, 'https://1.bp.blogspot.com/V_9lzdOZ7Qn58twtjSmdUCJieetlPLso5864FrpSQvAXtW8WNPbuH3Zd83EnH2NvvM28y1WxeI-QAFHzudRgZ2fWzbth9EsXofMsTC_nW5szHMrQ-1PpKwL62R7A42KaW8By6TTvrzo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (830, 24, 'https://1.bp.blogspot.com/D9OribVgiDaCcrvBc_Evy_y2AnXQWbkZvrBovCtPe_WeC9TyDGL-HJ9KkCywu5Q7BcXAsFD2UmlPLMR10IZKUMN3PGQ3etPOjthz9QHaLSvn3D2L7TbRORZ_NIjYukGjs3RRmfbr5LU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (831, 24, 'https://1.bp.blogspot.com/5HXG8p7j2l49YL8_kMsReArLu3V8kfFzJATlRyMyEMItdCFacRaRuO-f_ybXrgtlZ1ZoaVUjzjtpi__jmABdh6bmgIs8MxLTQPXgRU8p8SE1Rp8l3KXxqC0R5SAgzWdKaC_YR6xk7jY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (832, 24, 'https://1.bp.blogspot.com/SK2ucN54iuLOsZyNkTIljfE98Bkm3KMk3WFUy5FV5h4LLjEYEsNuD055dQ9i57kTgZAoVTegSwVOYPb8bzVPp65lsmT81AYCjsUrBSxTAYLO36bZ5I5FwkzmNjw0f61jHOXa32G0amI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (833, 24, 'https://1.bp.blogspot.com/KocjlnxEUqX0jVNOMOvWw6S0WtNKAgUSfUpxOxq5au0i5IyqPf1vr8fZKP7zw3hihnq4XUfJByk-VVava-1y92MREXhpo6rNU0OuIHmxKMut8e7ruf5fYk7YBKWpR0Qg_jBAiRk746M=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (834, 24, 'https://1.bp.blogspot.com/YBWEis9MQfhBB31FNIcCrOC7s5jyOjJok7vvZP6AFp15PaBTJ4YbBWQ_eRlaxEUknmPEixqESJTio1QxIfjMcLlDGRiY2HejlyO8vC_7yNj5_0zQzWe4H5aBOiQNHORl4aoFMfiiWJs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (835, 24, 'https://1.bp.blogspot.com/2bc5dgLWXyZ6yqzMNSp1em-oAot-dvzNsdRcs9mvEFwzedk2o5bWLH4tKZf0idrjntp69-miLCheXycMlzV6jwF9GoN7OafiZFu5QK1i2Ym-gkYvFeZvAzvr5QreFF37E3WgdJhaXUY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (836, 24, 'https://1.bp.blogspot.com/iMjy70vFb6gWZyIC37uhOf4K0yWLzqnpJ2kGdcd20zhaHTXCSpFADeAlchl3yrila6ar0L9nwazlQ649mHghhh9sBl9Gm9Hd5IcQhK9Txv__ZZBBgV8COF8J9eGtme3TzOZl2NKUSxE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (837, 24, 'https://1.bp.blogspot.com/HNoexL4N_2J1o0ACufE7QsHtorGPcMCafMpUqMNYAVDGIMinsTE0SqSUmBbbfwIMYCMauSfClNC59w7U3TsqQXm82A8h7KYU_odAM61-CCWPOrMLKLFBU174GpX2spTdz60_B-f1AlA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (838, 24, 'https://1.bp.blogspot.com/qBrn06nM39LbBfHoa8-MxQOUxQFMXbK96rVOnhX0jQQONNpoEzpcg9OsIzAetVi_MFstSDuVnz_wBIly5RTz-CKELg_2Eix6Pb5abPGyqdYDyVD6ZmnEmu7fbla-e_-fE3-cV1PSVF0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (839, 24, 'https://1.bp.blogspot.com/4-frJYdCFIu7U7UnIRF4lQpfyR_JqYRTswXHog7npW3gW3J67u-e8TZU6b6VPsnDow-3soKRvMr-rEz3_fxpxtBczW9VD0N7Trnw3DUQDZ5OQmyiZfXxKoLE1CRDU1yXCA7z2aP1T4o=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (840, 24, 'https://1.bp.blogspot.com/iOVHFTjyhh_Ui9pdWfQcdbMKsJQBy86PAA9a4sYOQp_YLBpWwdinBebAmeeDd4r4Ax04tIuHHozv-WtlUf9jAF54lzvi-i9dYHH7F6Gk-9DC8qrMARAGKhupkNUY9mwFDvkNjLbqoUk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (841, 24, 'https://1.bp.blogspot.com/5Ln6abnDpshfAxHg-cMYrGymfWM5mW4GJFV6Ee-EIBil6lLIYnZfKobM9kIxLQ-FS9WzqNxduKyqNY6FT0rnNYXVf_KPSMW9BKC4xNWvopH8O9OhEyIPkKOhgHE0pMCBCTgd10AWo5Y=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (842, 25, 'https://drive.google.com/uc?id=1dxJBnOR7cL8lkG4q-rTjrB4fw5GYJQgy&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (843, 25, 'https://drive.google.com/uc?id=1dvAkH_JqEZTgJxADymuxQz2TBwlkJ0mT&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (844, 25, 'https://drive.google.com/uc?id=1duyPk6NH8CXnI5RtC65uEWP2VwrlnHZW&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (845, 25, 'https://drive.google.com/uc?id=1duwGLJ9F1PD3FHoWDClfkernoMe3hbpo&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (846, 25, 'https://drive.google.com/uc?id=1dtsNMHRGRs1AxU0EclACR0P5WNHNBAZI&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (847, 25, 'https://drive.google.com/uc?id=1dtYvKf4fXaP5NPy627MA7M9MBKXOAxBU&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (848, 25, 'https://drive.google.com/uc?id=1dsd6_7NqfNxXgwvsqjTYh6zc2cqEus-j&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (849, 25, 'https://drive.google.com/uc?id=1dsSLgP8qTSmDh6E-bnldut-9zKBHz69a&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (850, 25, 'https://drive.google.com/uc?id=1ds-zSfy-lHBo_ZAegDuCqI8YM9DaJf13&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (851, 25, 'https://drive.google.com/uc?id=1drhvOM2Ykv92JGyw5rtTCrA3vfKyIo5I&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (852, 25, 'https://drive.google.com/uc?id=1dqnbwQdchBp0L_D5jwhCxpAXPn-X0M-V&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (853, 25, 'https://drive.google.com/uc?id=1dqiKiNqt8pDVYX1_o7X6OKwKQ6tv8NVm&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (854, 25, 'https://drive.google.com/uc?id=1dqH1JEGjBaq3b1xBhpY2CncWnRDbhCla&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (855, 25, 'https://drive.google.com/uc?id=1dq6MLX-o4bF9UodWa0LnoZymO5GMOob2&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (856, 26, 'https://drive.google.com/uc?id=1dq1IZk8aUVi9AaCJfWwf4a1F2zilDDqE&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (857, 26, 'https://drive.google.com/uc?id=1e0QcqUzG1bRY8nSnyhGkUfhpkOflMDgb&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (858, 26, 'https://drive.google.com/uc?id=1e0Pn8SBC5zUlRA0osJSzNxcVh6T6-t5Q&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (859, 26, 'https://drive.google.com/uc?id=1e081khnNc2518DgJ0wFWEiSlU_Rf28t7&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (860, 26, 'https://drive.google.com/uc?id=1e-L2ag21yqABDhUR_Yy818HgJ2gXBy12&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (861, 26, 'https://drive.google.com/uc?id=1e-EUwqSXV_VmbIY5nwBNxBvzUE_1b2td&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (862, 26, 'https://drive.google.com/uc?id=1e-6yBEAO4QuQjVlDARrVP032hlQ3qRzJ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (863, 26, 'https://drive.google.com/uc?id=1e-5zPNs3zjM5WTWhgPj3rOb0KQgzuOmW&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (864, 26, 'https://drive.google.com/uc?id=1e-04Ssid15k_Rs2ZqlSI-XAx6ZcMDTKQ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (865, 26, 'https://drive.google.com/uc?id=1dzwQ1NXrBK6-vrH37gViGlH_tVW5FFQM&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (866, 26, 'https://drive.google.com/uc?id=1dzqQkl_QtHkHVsUOjMrLGDfcEkWVQTO9&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (867, 26, 'https://drive.google.com/uc?id=1dzimW16FvVcyxBkp3cvX-YdAo61ya7Vj&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (868, 26, 'https://drive.google.com/uc?id=1dz42HnK_8DiSCp0DzorZbVv-7YhcOTdc&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (869, 26, 'https://drive.google.com/uc?id=1dyL0OtinIJpOMbPT1m6_452bhydLs8Ih&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (870, 26, 'https://drive.google.com/uc?id=1dxdH0jMgEEq3k1u3E_etpJG3EpUmyFND&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (871, 27, 'https://drive.google.com/uc?id=1dx_1yi8ceXGD5bcJOtkSBXcLNoz57u6n&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (872, 27, 'https://drive.google.com/uc?id=1e6KhiGrtS8O1oZIDm-BR2Y-YDaanG0AS&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (873, 27, 'https://drive.google.com/uc?id=1e6AdXP6fsQdl17qvXWwARxEMat7kRESN&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (874, 27, 'https://drive.google.com/uc?id=1e5fnZzysc98yUdBwv8UJ1-jYRfh2c3HV&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (875, 27, 'https://drive.google.com/uc?id=1e5Rok-VDOfXmC8tKxNYbPOvEOJEeNBPg&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (876, 27, 'https://drive.google.com/uc?id=1e4y-E3V2jKyG0QzTAfAsWkrF02938keW&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (877, 27, 'https://drive.google.com/uc?id=1e3vB_ppGTowqA_hJpHYyF8MPv6wCJYlo&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (878, 27, 'https://drive.google.com/uc?id=1e3L9oHXX4IpVlD5YTVd_jJgrou0u5NPR&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (879, 27, 'https://drive.google.com/uc?id=1e2kT6sv75MH5hWor8ujCqWD6ZQoZn7J_&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (880, 27, 'https://drive.google.com/uc?id=1e2_pxGwrKlLVeVx8yZmbjb59m33vjsWj&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (881, 27, 'https://drive.google.com/uc?id=1e2AYR0nPw42ugl15jExVPdvg8u94FvGe&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (882, 27, 'https://drive.google.com/uc?id=1e1PTGqtpdp307mtGorQjvW0kGnRy44jq&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (883, 27, 'https://drive.google.com/uc?id=1e1FLHFTjAw4My3-MWyP6_FFWqTgtdxC8&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (884, 27, 'https://drive.google.com/uc?id=1e0oKjEgCVwJwSlT4mg3iAsmvg3jDtjcs&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (885, 27, 'https://drive.google.com/uc?id=1e0VNFTLGTwCxA3-BQH3Ovhpq-avP7E-e&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (886, 27, 'https://drive.google.com/uc?id=1e0UNQCBvbeo1bBPAEbWJ2-LwqLgxW_Pv&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (887, 27, 'https://drive.google.com/uc?id=1eGKjXC4DVyT8Spx_bqmgsjTmFNUI0qFP&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (888, 27, 'https://drive.google.com/uc?id=1eG5e9U7sAyozIfAUT8MRaxVcWDmz7tES&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (889, 27, 'https://drive.google.com/uc?id=1eFsaVT6L1AY4n1wvLokhezwwXKFJX7yq&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (890, 27, 'https://drive.google.com/uc?id=1eEo4ZaG8FCEEePEsCQ611_eQngBysL7D&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (891, 27, 'https://drive.google.com/uc?id=1eEZDBiOCnLIQh_C_HFj1p1GGTumzmK7Q&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (892, 27, 'https://drive.google.com/uc?id=1eE3pR9zRatvOer2-2ZNzYAwxy6bukjil&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (893, 27, 'https://drive.google.com/uc?id=1eDzDuLIwwhFXY0BKXzZGhR3uWLpd_x9R&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (894, 27, 'https://drive.google.com/uc?id=1eDL86dosq9gZCuyITVUL3aNqC7UZXYdL&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (895, 27, 'https://drive.google.com/uc?id=1eDCOmmyTulhBGTwpgVbFasvVNbluVP1E&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (896, 27, 'https://drive.google.com/uc?id=1eBJCfgmhD-As39tFrGlIvdONQAvORQ6d&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (897, 27, 'https://drive.google.com/uc?id=1eBC9NdG11onq7myTSsDUNsw6EY497D-0&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (898, 27, 'https://drive.google.com/uc?id=1e9FpUhyIhH7KzmimspwfvjWPO3jRu2dO&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (899, 27, 'https://drive.google.com/uc?id=1e8psACsxd0NyZ1FKZ2L9ad5lp4noDaF-&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (900, 27, 'https://drive.google.com/uc?id=1e88A9c7a445fjC5aylVdxXs29d6zn4Gw&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (901, 27, 'https://drive.google.com/uc?id=1e6qOOoNbrnNuDKgnlNBUgNgjEwUM6vVN&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (902, 27, 'https://drive.google.com/uc?id=1eLlbqZ00J9gSfdyt1bW8tOu3bVQpahTY&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (903, 27, 'https://drive.google.com/uc?id=1eLQjufFpJH8B8SP59XwSjuGwBfNShdg8&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (904, 27, 'https://drive.google.com/uc?id=1eKY6uj6zWVaaVfeVDlTKc9rJ-wKScf8U&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (905, 27, 'https://drive.google.com/uc?id=1eKO14zBvN2U39S49ndlOR3BV6T_30zCd&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (906, 28, 'https://1.bp.blogspot.com/VUJrpcYt8gvrA32Lf2kguiaR-Vn4IbwziWycRAzzWLXrOH79LkyxJJxTHVc9tBxMD8ztPwrIwuuwZ1NAnB6CT9mQ5_jt5i6S78lL2ZbuigzU-NdG6wOdXxLFT43LQubkHVkspN_zdA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (907, 28, 'https://1.bp.blogspot.com/FyQ9bCsPt-ekXLmWtg-ohxePdB_pH5t41eRbgTc3apzOnNC4v7C2oB_2zryYtQlFfy3d2SHYcTPwXERwOUVV3yxzI_-obrXyWwsUHiqehRJmzEJAZTobswlqzOF1Lbc9nqQ_csZ4Zg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (908, 28, 'https://1.bp.blogspot.com/EmIE0EuKdsvQ2nCEFU7uk6k_ocEqwhh5l_6ys0VFMu20gZsj9sODZoGLCteRQlqVJ-AfYQNtWCqLxUcSEerRDqp1IhIwa1GFegamBPl7NiPwgFpQX7tXnH7Usfp__q9ts0oK5fnv9w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (909, 28, 'https://1.bp.blogspot.com/Y8yg0wfSprjI3RxtxcFVoLigHvmRHx5-kXJ1GwDjpQAoDEEk597HYgyCL6U76WkeHjkhrC30MMBpcHgYMxp4wKIF_meWygyI-bAlrUJl8SWSky_uXQSkRikuRYUelOJRHm6blMTVHg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (910, 28, 'https://1.bp.blogspot.com/8Q0tPFnsU79u-op-qyQDoOT_fWrBR9zo8oSTOHGjEGUKZT-RsjKFPmOwE2QpQA82FGjqvyepcK-wGdpXvNyCYsDxlnrQ8ccK3GUYD_2J6EVpWJrrD3nwONtNJBd8xHsHvZpiT1TJPQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (911, 28, 'https://1.bp.blogspot.com/8ZvAL3sw-lCraNX6ab3VTyWfBcF3YDkVsuDTcPfr1GLHaltMmCNjD8J1G_TntCzpx7TjpKjAqD7BKCVhja2TDaw_1kDEDpiVXVYrb329xs2NHBCecXCVMIgub_Id3YcbtLausiwZFw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (912, 28, 'https://1.bp.blogspot.com/IOg2F576bkGNj0Nkdh1bg2DxPv6DKzOEWFg-yld5cbYVbp1Wh3JSWB0UWsGlq77lqDZMgKWQpAn6wMOg4bEXThtu7AMw48STCloe3E_EBUVe4zM40tWOr9pnaW4IDfHRK10j0noVlQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (913, 28, 'https://1.bp.blogspot.com/J3oJLo24nUQK9jcvbNpz9TSiH1UX6MTMaR3JGu-mEnI6l53IoHUUQj6WpD0evzVqvuIYF099eLuZENugowywsN4e9-Jf7jbY6q679QGZvwYpMAVVEAy1aHBCuF1q1fnRKgo0U7e4Vg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (914, 28, 'https://1.bp.blogspot.com/9uSuevr0uzHWcPsJT-4PiSjyS4419o_C8eWs8Pp2Se2Hc7txZ6mZIaky6-9c6Yyfzl5vmm4fMr2IeJNvd-J_u6h3jR9jvjw_NeYAD-ZRLmkjwSze6xfN3z2d0WFYRGaQmGobSkJRYQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (915, 28, 'https://1.bp.blogspot.com/in7vAM1ALsjk8D6n4wBPPwDZSe1IJR-pyMuFjVy9jRQeK-sZXH51SsNAbQc1qDfq2GmBMuriz3IfrzpBjiBor2i9iuwYRtCJ9HZVSfWWaJRzR_LVNl2E0uHrgEy0MRFxhzlQ-mv1nQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (916, 29, 'https://1.bp.blogspot.com/qNxyxDrNPDl0r2U7urjFp1nm2xYE7uDLzCAurC0ss-0z9w5pu5_KIPivBypHIu4QPU2MlBYIlUwsPO1eDHCzSVGTEInloqxC5w9hbrvbWK3SDJdxnnIx2cjtohdjcperdT8zf4UP7g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (917, 29, 'https://1.bp.blogspot.com/Y4Oeq1JZJj040MFwt1i4XUoR3LTBzSx_fPsKAHi5CC61RdVMU7gmHnB3vPBu4rMg1XcFYKo7-3QqHfynWu_5xdgqeCMknGEmR44dS39vt1rROVxcyffh0JLQIpuKv8yllqtVsHL1qQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (918, 29, 'https://1.bp.blogspot.com/39jZV0H5TjZuZTg4TG65qgJflNnf28fJFjTbvkYP-CX_1f5hZlPtxKZWqAwOA2KqorSWCWQ4ERN5aN9iGrAdrdMlJeXktzoLjGM53zU4Dh2qYb6B-vOwozLwIplYrVX1V0fRroPSBw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (919, 29, 'https://1.bp.blogspot.com/jm22LoKBKtdxSPB_ubo-yYXub4VUQAIZk58D0dlYEmA3uttWI9IFJGvGxXHfjWp9Mga03_Aaw8QGdVS848qkzwxlTnI6Nm5ZxI5CXLBEkLOIig3zfrlf0Bmwe-ahBVkRbkQEGPt2KA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (920, 29, 'https://1.bp.blogspot.com/gKl9-burYcdHmr97lzs8bffCkWEHiDAwE4LPVUGCOD9Iq9dUY7RbTVXSMsMzlGIGGsGZ_dU63y6G9YrTI62txGQoc8j020yf3Rvkx3Eb8-tm87atCacrkNf5b-6BJ7wzSp3XYtA6Yg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (921, 29, 'https://1.bp.blogspot.com/j5jRb58o_uIWxkCGEKD4odapIGqqg05YShWCVQkstHBu8KpAsAQwPVKHZcidKX13l84NA9d1UKsz4cwYIQYgV8GLT5TVf1HB8cVVCFIiaCy10HwucdOySPg00oDQQl4yQV1xOoowoQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (922, 29, 'https://1.bp.blogspot.com/YyB1MVJyLcWKE_hx1bGcYJUeEnutXdl7uKSnW63B2tErczlfpuJ4UnRcllrkTA8LEB-vUZtQ1Tbrhfl3rG3UqPcS6i87Ci_DmI2EGUi-DbOS2aDXIj1oa4csJYACl8zfoRUMvzkD_Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (923, 29, 'https://1.bp.blogspot.com/XGsLutpoVesIMDBa-bi914W2_h2KD3ZQriEaDPs5_uu5TnnSV-7DxKnvgkFAu8cI0jhumGtan-gjyutoZLsW6pdc8OKLezYRD0nWXQsYWYY5NCTVLtTl6u4VWVURtVy4rmBPvHNUzQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (924, 29, 'https://1.bp.blogspot.com/O14saXajsSHns10guwDD-o-u7CnjEX1WUtYBRUgvN4Kp_vYzMzBg8xTWiXRHsKniMxP3vY6xrmGE_2y1ZHLSnjUJt9Imcp7rsIlMeu8Ka_JxbICvF8e3bn3mQ40ZmZytqcvrgfwgUg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (925, 29, 'https://1.bp.blogspot.com/pMZMpilNAV1I0ym8A_ga4tI5ZzjeGiuRFqKBrmWCdvuNVu315-xA-h1g6g07SrolBlagiFJQuo4UtFy_SuZbR2omif2AJMYwhmoqixRLKVFcet6bBPQg-1OqDC7elirfQzK09YVfDA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (926, 30, 'https://1.bp.blogspot.com/yMaJqlMcC1flcvvnnCWI-IiO-S-qN0wMQcGxeFQRxpVHCK_4JYUaM6g0t9ntXINQeB7qJpp1YvN8AQ4TuKHF5kFVlbIJ_mt-g54i84FfUI5VTe-f9fImpYfRGEwwNB4B4h7zMHB96w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (927, 30, 'https://1.bp.blogspot.com/-1LFvcL-_0jy7o49GfKYozGCL9zeb1oG1W1BuDQeGp9u1MTaEnd724c7RtaaXw-pLj-skyZE8ionHUO-gDI81wajjMQun0azpFZZjTRqwr6b2PLu-I_BxgkDB1tXdRSFE9IYjVTN9A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (928, 30, 'https://1.bp.blogspot.com/F3vj1yeMKo7J7zJGnKaiVkSqviMzNsJhcY--BGPaQdRS-MX4kxAb30VtnjmW04uYRlYPInpE90RXZmnbqVaW45cW2-G2Xry82C5d8mqlzMqWFGFhTUjocNbBMZML9RoWNym3UoxOzA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (929, 30, 'https://1.bp.blogspot.com/Cdr9RzUps2dqgU05iYuxaz1paKGu7KsLQDSL0uVDcYt8lfu8dJqqJ5s9_QDIBeKVW9M7npKZZDaK5htRoVRFHzgZQtoxBAFJaBn1mb-v2QNdnpltr1kKw9-kMq_lj8ok-W_qfcgNdA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (930, 30, 'https://1.bp.blogspot.com/81fh2qmRKXncW1HnwhltdSSTQhBfLbm1BShDac7TnHVrvrRnoUealVr_HRH6Bd1iYOfLFCCM2eiv6lI8nc0rO5Db4AtMYL8KSwq_2Id91-HZrxoJZXXDT8XLkyBlriNuDG3BFjJbnQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (931, 30, 'https://1.bp.blogspot.com/MBM-0vaDFIo608H5S_6OhYuVHAk44o_n_MbZ4za7xgprJ8QOW6-4EQAkhNPnndV7rO9r-nWeDAJIFImn4JG8bFKbTXjGSjt9yk5425axiV_uIL9EiisXnRhNochgIenEAAGIB8RPTQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (932, 30, 'https://1.bp.blogspot.com/GWWuCw0LuGKD-omkV4BhqZU-yd31EprnrFnOiaeoh97zrlM2QveJClr9boftIKbzLyz_pYAN_zGjbIn4V1zM27z7SqbHePGWfmo5D8UWt5I91t8KPsxSazMYnswrLP0DfoUz0Elh2g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (933, 30, 'https://1.bp.blogspot.com/yQaQt1G-h6JYi54GOFnR4Sntz68rRIh8Ozgzg7Y5NzW29SI1WJohrsiECvQgEoWJU-ZYORg-dM3quwJ6HN_LIoX1cV5vyf8Sm1xr4bRXKrUg9tXG2P2HVHvILH8jiu1PTWTey9wwow=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (934, 30, 'https://1.bp.blogspot.com/iNIvQcCVb3RJ2ogKB4h6NgerOCv7E_oNxf3CoKMdlLqLs-VnsJb4VrNFXbkoZso9s0YSxf_N43K00AeI2w_2NH0jYtK4-TRALjKLe19h2zqT45u2k5zOPhVOyRXaIXs7V4wndl-l2Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (935, 31, 'https://1.bp.blogspot.com/2R3cWaj13c1QHh5iaddlWjkvBT-YFJI2VYpfphKcLaEM1JpAFjjUc1EYCE3izNLfNCjLNCL3X_8jy8Md8PfxR3RAfrIpfd32UJApdlHsyp8mvkH3W0-bOTF8PPbGLp2VkMDAh2-0hWo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (936, 31, 'https://1.bp.blogspot.com/EV_B7l-_n3fnn6vc0l3HuDqxKGoYqfJxUGzojhMgLr9hh0HKFOpl7XifgpSQ87DVURlStmyF4siyxSUf0ZgN3biLG5-FZMaZfFcr2rCyWU39nQX71TxnHWbCgrRUxkE6y9JhtKfqYfw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (937, 31, 'https://1.bp.blogspot.com/51W_WjDm7pMZdMF4DiY2fzj9dbCzqeKg19mTsFROCexxe-Kb8dGBuL_fh9fAoi5Q6vB2o2mnfbzC99lj943HqMU6Y1Uc9YlJR0JHNv9KSF53UxQyqOs6TQGP41tbu11-l6pSfDpl45c=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (938, 31, 'https://1.bp.blogspot.com/zOC5WBc3apbd81PfZagHRf0sBlsJv3f2m7gjOIG9e0H37Q294J9x1Bwj3Njdbsw55H8530qB7ia-VHdopPEw_wqOhGOUa8MfecUkG6GSfI2yir5jhqWpHDySxJkNNz4oJ_rIVnctGnY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (939, 31, 'https://1.bp.blogspot.com/AyDIpyX7btgREEoAQ9wVsGiwSIlrLw1gdp8a8gV4Ckp9hBFBDxpNzPXubiM0oEufysHnB0xtdHPGuQg5zIqWNxVWxnz4RhYLLJZZKuo5x8aQ8M6nKNVVlv1tggaHzStrSlDXPx-CHUg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (940, 31, 'https://1.bp.blogspot.com/7m4wLjLmUf6Fp_xq6jLOq52-j_Hu6l-pR8nlXumRcNFOM9FR2IU8GK3JRsZGUz9CrXm5G5Q6unSLKPKg5U36CAkQIjMhQTfBNXlb6Tbyt3G-21s_tl9LixluUfY27oXQss6u3JjNSq8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (941, 31, 'https://1.bp.blogspot.com/hNAjYToA2QAnrds8ijcVG93NZuVcNBsqpvS9DYp76qFv7-yVB4YtQRol-93BpHk-pi24THooE5ZJb43s39_zT1dHGuAPoOvKz7hP6_kw7hGfMhdqH8MIUCI8Zvg_ALEpGLZXuDzHwqw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (942, 31, 'https://1.bp.blogspot.com/5UvqADTPbMDB5Zbp8XWc_j0hBB8aTLnvHSVDEi80q7WMow_kBaq661b8HPkRS1bSYPXrxQl7pP8sbj5DRtPsd_tWZUE2NM4xlnNgyeLhiObWWsvVMm8pAROoAg4jCM-MjvRNoQiXIXs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (943, 32, 'https://1.bp.blogspot.com/kiVQL-JYTp_twUwMAyu1bAECU7GFjreOzy2wSU4mrJNphHg9YQV5tfzU330HAFrG9jBzdvU5PWbxUiE96JozJTOfDmA1AqY5AaKCmKSHnZtjchZ5LdrMS9iADqt6guD8ZKdBvc1lGrU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (944, 32, 'https://1.bp.blogspot.com/MGIpd0z0E3Se4yo2lplDJ_5bhsI1mSyDefpxjC3UA2GVqSH6ClCOW34seG2sdcafaE6TvEooz_lim1_H7oMh2QpKOsdqeC1O-OlBOA_RqWHrh2vp4xanp6Kc_HNsy9_CIOSh9gGtPBw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (945, 32, 'https://1.bp.blogspot.com/vhP5lER5gxoC3-hbS6UM1stXzkBmK4EPUnK0P6hEKylIrp_A8qF-5Y2lQP2UaKMJOJZUYcXP1q4Ld5ESHYSCOZH5HWH9Iq7uPWEDa_nooAV_c7NurnHGfXB6up27TNZGWU7vIIK0xj0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (946, 32, 'https://1.bp.blogspot.com/PTDd5SGrXI1S1Q6FQgRqF_S18NKrYnjnVvL2_HW6MRsYbhKB9ZcZ4o-BF6QN1tqPvnQzwHpUAYVwegBflVfE04zRtqJOmFKwbAtfBeXlJ-Ju43Umvz4vCHPN8W80v6GymX1ndz17Mm4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (947, 32, 'https://1.bp.blogspot.com/rpGDUCkc1hpQqem1ppmV_x--SIjBc5ZhBBOEC98KxcHT7EyqLVKNdGusLFMJvpcGxBT6MAOoJD7_SY5mWhvpCQxuu2RxeK_WZnhW09YaFKVQDOCh6Dpi0MporPFzj2DUbsAkMgjZMrw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (948, 32, 'https://1.bp.blogspot.com/u41Ac3cB6Fs5iYSXs6-mQ3t1nhOqvlSa15T5niaU_LxWPyHoDHUQSDM60ZHO_qw8i58jRQP7YLIpfUUiShg9GiBU7lLQLnQuGLqsBYkVNoNDK51_9iTui0HM7hVbRTND6CvTbhenIgY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (949, 32, 'https://1.bp.blogspot.com/aw-SZCjVA22AbY_5IYcAUza-xmi5uaXfZWtjGRoy9ZGLb4Xb-3HJhwKqB_SK4WzeAFDcVnJqVcLdooJhmngdilw70cwE9j08ekQoOofKP7uPyUwB8Mmu15g9GiUkFEbOR733By-4CFI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (950, 32, 'https://1.bp.blogspot.com/mCQ_MoCMWXiPUmLDybpocg2-ydMAkArjqLUdtZNq8WhfVPzzEQa5SiI7O4gFxkXnhhTltzeq51TfxnIxFYC4OXL6tiIjx9Y_mT4qxjPx-UMNq9dFRtmrAjQBZ81_PUUqdn8XPQ-mFQw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (951, 32, 'https://1.bp.blogspot.com/do-gEJgsOIh8Y85kPuSjzsmb58wJS-Fhnr_w2F1WIlVmNm_ptBD7MH4qVcHsxY2dazDMLRUHJwQ3Vj7F3jF6jo80oo9r4QpPbcTgttS9w86jiMaiXTjP7Pg_Et2P7FsRo7QwqfO39Zk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (952, 32, 'https://1.bp.blogspot.com/n8PLpKVSww9Oyc6G5pgsceJHhBm8Rjc6UjC8x6rxG0teyJq0pHRKrnfigzoTfpWWjGaNu30sMx7LhpiJw7gKd0FdZGPGEuJ57gkUXW7UZDq0VuI7Lx7RcBI46Swp9-U0MGbeq6bYQvc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (953, 32, 'https://1.bp.blogspot.com/SftsKvVTmn6QkvKa7NIS-esaW8FMIWwvbUbIpNJi0jQWefL4CGAs0r6LDKNIm_xSpYHVglwuYymnFglUyGcUSA2leUYWw0ZgVQjHwrqI12rppdVb1oxjc-0j-3M2A8813gNGZOpMRms=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (954, 32, 'https://1.bp.blogspot.com/qLQQQ5zxOgE5mwgr0_58JvgBj-R78MnR9SW_TAInqh440BmFM-ZHXX2WgmJ0jfLLMTH5zIzF3ds69yeL2IrjU90LyKF31-6zorQ9fUDYTxoBhEtFqs-SpaR4Lcmq3WxqQrkvke_eknU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (955, 32, 'https://1.bp.blogspot.com/o88oJQ2Mb0RgJVLbWO8Fwgc0D4IZ4KrxpMaqEI3LephK_IprQNwvrQeC3qDHsJiI8fVu8TGOPCm-M1Sdpb213_-iWyQHo7P_vaTqw3fBoIq91bm4v2M6kI3a2BTIqVSwIVWdyQ8RvCI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (956, 32, 'https://1.bp.blogspot.com/BC4CptV6hn5jIYas5rvbO3S-3YIy6L4BDGCy5ChBOzC0frdR1Xmra9t6AGi2o8Vwu4yyxNMPZrMrGbFfHHrJMTiksEqP_KDUIb2dlTxyeZseAhx7a4r0LdfP-ORLxWhOnVfyoh32mb8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (957, 32, 'https://1.bp.blogspot.com/sZEST-A3IZzQ8DwM9q0S0LHLehrJncUeiJq-EI3pqC7uNwf0z4pr_1-_eR_scq-pKJ5tdN7xNtdWBt6LS8iYFd6K-vC0RoVmP1rNkoBIILXE6fCYdsRm_DPvZia5dIB6YgmqFclbe-A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (958, 32, 'https://1.bp.blogspot.com/7_vz7eCGOIt93QVFFldnlJytfGrfZsiS0fpN1PNTiNI2JgChl2PFbW2DgeoekePMtlxhOTIFy9kjBPAT5hZk4AOyvowKPibr52_JrJyVsW79Fidee7_oQxJpzMzQFEovYkIwarbz3eg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (959, 32, 'https://1.bp.blogspot.com/Cfjh30GkB-P9QOx2O2XWGPQJqJmmIuIYAXuFEixxcxLVsBa0cvqSmPmEenvt9HvTxaAyH6tvVg_3EQks1aeKOq6dVxiT5mOAN7QSHYI9zR3Q4lsQMzW9GrlH_iFEUgUKgqXyOJBEotA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (960, 32, 'https://1.bp.blogspot.com/l5A3rAVmVJo9DioJOeTclrxkJWH79XEGT7oKDitMGd2ckBsPmvnZ_3v8uOub56f-SfFigvtLepvP3E1GsMBRJGY7NWosAQHQcxjFAVngg1e77Bqp9JIzaHDDeJkh7-DMOwfvYvmYkoc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (961, 32, 'https://1.bp.blogspot.com/a1e21ggcYhzmLj9ldgf9ga9-Ian-JTXlBuGD_qGANoE2oDvaa7_qB7cXfEQQgZyL0_HZvvWBnpj21Q3Wusalbutds1ZQd2dN7j5NPvmi4gGKhZLrXDuc0g-_8aoljkPy0-g99SMeDiE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (962, 32, 'https://1.bp.blogspot.com/8ET4UmPI6D3bqHO2F9uIQvwv--CAmOu4ab7l_Uq7pKquuUBbhsW_oSEzVkxmZnZpgpc2MVFc0BlPHAmFg9e1HZUlApKlR7D5gMHqH4WxZ2LG941OvnCZnClj6NZJBvYEa-Y4i01Neh8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (963, 32, 'https://1.bp.blogspot.com/qPuIC0ro4doBzhCnQixt3mB0Kjv-s73IA7ELJ5MOcIUXfySjPxsyAvfzL_kEw3aVcgyjsVjVw8y520iE2_I7PRUjsC57hPwpybHQKvlW964aXYppZ_MOG5g8FftDYelL9Z6r86-kOjM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (964, 32, 'https://1.bp.blogspot.com/hbOPNByrHZWEdAijDMXer7HfYvUtEu3dP8LujMUF2YkWlxxoNEIq5VWyjv4qDao0oYPru88LM5uToF403G-QLDKczkrP2AJiqA6UODvgkFPyDqra4UuIecsCQJnkLI57B7mGZZYkZ20=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (965, 32, 'https://1.bp.blogspot.com/IuSdxt9zxhuhLhEF1wtgw__0IF7pHimGdyyDZYT_hcXzCpEZ0j3lGfbkIrMO8XvmTFekPUT1nkvu2ThPYMi-Z8f2V3QIyM9yuxh6vWN5wDwVEJori9EJFDm1CD07m23ExKnlh3pN5Wc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (966, 32, 'https://1.bp.blogspot.com/u05t5LJpei-S8TuFLjgWtNqKbM4jvMJCrCosatzigi0BHwoHJ7dZMgoNUXR7FBQbAHHQF2W8NLVIekhXWtCFkMz1GsAsGq3KvGgGnIawoxd9CTb_zrZS7xt_PJURZFEZ04df96h2cCM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (967, 32, 'https://1.bp.blogspot.com/IuDKrDaOVkZk5sxtp6A2jAoQ1J4_juXSpjBw2RQZlSIQWKC7WUiD-ZyL6Y15hHiXJIKm6CyZlguFq4v2qM0o3j94sXgALj46FFFoCI_QcJpPQgeSVGW2ous7SexM0ubNNs2cP3iCR_g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (968, 32, 'https://1.bp.blogspot.com/AJEcs2R5EhzEAt2BM80V5aQBD--axqlbVItdd9sIphAiCjUWkeC9zSqntB55s6bfqsy-uGlRG1hz53y_elTLqsIZEqpTQN9v8SDPTwz33cxo6YvNKQKTfvjsKAtLZkMQ3CCITq4uSAY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (969, 32, 'https://1.bp.blogspot.com/q6WfBkoO75_GWaukmUnMNrfNArbm1tz-nka-rcakC5yDXysMoLGq3iWCBUPQO5ZdjIOSfcle10HXFoH-eqrdp662VCQ__aaF_kxc-4spEgDl9a2zXT_hCPk__OtneIgJGq4kNJ0ax-U=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (970, 32, 'https://1.bp.blogspot.com/jIV6rsQ_3-RARWwWkv_BTorUYNsmUIN3y4f670_-yk30mllVyf4wMu48qft4fuFNRs7vMtZD8lNUaQQ-ms-b73IJjwNfya628WzSus3hPKOYqh-gbvbjj-sjCuZexqlqqs8RTw82tQk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (971, 32, 'https://1.bp.blogspot.com/Lk82x5Yo1P5cBjILF0UHXOG_v1qeEjghvDBtBiFfl8CZkqnWQG1vLXk48u_O3fCA1AxWutpOliYURRehLmzlAvvkocM3L-fdoS2vTrrO_mroofQg_CodKcErInh9GXbx5N45n-Hyi1U=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (972, 33, 'https://1.bp.blogspot.com/b7BoCBGFZwcTMYK2tiPrdcmp7EXeB72lsIMGJ_sRqpH8j0WJ1ZjiDHr_LINvmtneM3TvgkIyZ5aItPYucVbzGDLTBclYqPqTUE1mtAkcdAyRj7QfdLjLk78sNy60Ntk5AaXEInj_2zY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (973, 33, 'https://1.bp.blogspot.com/9o0UU8wyVTVjr_bZnJ0xpjqoeou3KS11sUUkdZN_PzkVMfkqeXvKPuec7mMxAVHmnvTE7ba_Lu2GJa97wblGygztppp2RT_87ZlcPl1cSC1BFbC3z-obFDq_D0bZohRJdEcjDczq_3M=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (974, 33, 'https://1.bp.blogspot.com/4-P7bOS4lvaN1lUc_MIIsdmwAj1CtF9cjU1nv3JFL8wfIbI7rICSrlSLUsUEgeikrV0P8gFNyPQkDgOVhxX-UUBYOYUIT8HtdkWzpcy9WjX0vT7ECrDq_8o1eCqo7uHz36Q2CHtpoqg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (975, 33, 'https://1.bp.blogspot.com/03bpgskvZhQQF0Q9cnlx9PDuxxu6U82U0oSH1_GZr9YlN0X_I-5ezHEkepmgwWGM0XTXuFhiVbSSVYFu6LSUvuOfc82Uo0Jw73ho3XFi_GghKdvWZsm0R723mg5bofDL4RbexUUm5Cs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (976, 33, 'https://1.bp.blogspot.com/9HDL5aH_1EQoe3pgegKJsPcRNVS6cbRXvZ0AcA5oIXymW5rAqqupWW13ciyw3xOro0GSBoHGnEcEdYm1qI_OwWVLJc64dLWkp92gE8tkvTy5wQ4Db6h54uMbFoCX-52BQr-lSviVymE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (977, 33, 'https://1.bp.blogspot.com/fFwKQNKnhy7V-oLXwhHbSi7ja01O-Ewa2qTAnVeIlxKsMmnN_0KrMxKANEDoWkWKXUgAsHBejLJL7oxaI3x4uVyOedIczO6aB8a4KUPU83xUjfF69AvXZKtnI6xwnUO3Xz2Xoc8kvh4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (978, 33, 'https://1.bp.blogspot.com/j8a48aEB37VpYY1hk9aZ72QIq-Jl_h8SUbWlUn0Pe8WwFPhSCnnZ5Ziea9ahUwGK96eRHEXMyj2d23uGT8uOj60tOQfqU17Kk_K4fgsNkw3EaLsvfEJ6UCKey0UuYoijxBVDmWjBQqQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (979, 33, 'https://1.bp.blogspot.com/HngyqbViZcHHVqbIno55zRRqFFFVjIWb65fryAKL3W8Ut7ke9degy6cRfwlTlReJpPEcQWqhpG2RDIhTDarAhjnOMPXUuLPwvc4uuD8-5V6s9QfsgfISayEMreEXj8lNU9paTzHscJ8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (980, 33, 'https://1.bp.blogspot.com/BUouZ9KQPmL_9-azgoh6KpHW1eA9gkhyKfkS8a73EkTGDeTPdxDe8I1mhZhJgkGDIa4sClJRw2o3YDPY4fzk2AUxT1S16yhfmqmrCje5oAe-R_LzJ36PWqkF7-PGB_sMs-EdVUs9JDg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (981, 33, 'https://1.bp.blogspot.com/6DU5b3cdbwFM8iluA1Y1-njihhXeEgg8-7-2wLvSsEHXnWMDnxL7kt86SwgORAp6VGicayY9AdQs0IkWXl9PZZx-H63nanOCp3FNNkCz20jAG8p6qjcTu_DQ27a-dKXypmLv2pRVOiU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (982, 33, 'https://1.bp.blogspot.com/XinXeR3LQA1V3hk4jeoLZYDI5ycqKnFODpbYiviAdamoPYkz7ejK7cgLv2bsAa0WGkr_Sbyvwp32aLwMHhbDWjUbWjR_jOkWZcHT1ADbPqUgbMsT1Di2PfwlhPg737IQIabbDzf_X54=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (983, 33, 'https://1.bp.blogspot.com/e0rtliViVE-I8Gjb8br9abDZRbUzdRugm6u-AFMT_9vixD-0g384OcKpJhRNv39K8ZvFEjbqbyshliDC8wBCUjdQaBqKQkAuGvwRNFPeA9_0fLuFoBrZKHVEtqGMxmtCW-O_bFs2Kyc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (984, 33, 'https://1.bp.blogspot.com/6ItWt7v-s616pMsgmYlcPC-Jil0eVwG5gPK86Z8I1cbwrmIsOY4r1h_2QzR2EywRL9_XEn8Alv4wuueWaFG2gEHMT9OQCRZ2IFP1KHN1cZWtrd4FEleCIyTNk7-zMgLSZBUnJEhdSHQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (985, 33, 'https://1.bp.blogspot.com/AdxNvuET1hE4UP0LX6pxkfFandKTkI732ij9ky5x0qB5vYFqpQd2IAEJsMzWfeoacL_wFyFAPiGMeoBrosGp9i35HhtTQIRxGxg0XHuim-H7W062MsOcjI4J6WIsIzYyHCPt8pEh8fk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (986, 33, 'https://1.bp.blogspot.com/GCLCxoW8PPSiz7jqiTxrFfFS4vci25A49eRR_yYTq-y2pcpj2yFg-HfPrIUjpmAYb42uvoaFA4izwk6RKJs6AHZg7SpN3pNSMORxcmLmUfHXweCJtFJIyDvNvQH-mvjd3PE0ctP6eXw=s0?imgmax=0');");

        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (987, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (988, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (989, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (990, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (991, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (992, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (993, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (994, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (995, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (996, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (997, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (998, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (999, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1000, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1001, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1002, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/19.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1003, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/20.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1004, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/21.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1005, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/22.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1006, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/23.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1007, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/24.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1008, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/25.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1009, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/26.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1010, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/27.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1011, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/28.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1012, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/29.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1013, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/30.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1014, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/31.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1015, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/32.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1016, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/33.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1017, 34, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-1/34.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1018, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1019, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1020, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1021, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1022, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1023, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1024, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1025, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1026, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1027, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1028, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1029, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1030, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1031, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1032, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1033, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1034, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/19.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1035, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/20.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1036, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/21.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1037, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/22.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1038, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/23.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1039, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/24.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1040, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/25.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1041, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/26.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1042, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/27.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1043, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/28.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1044, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/29.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1045, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/30.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1046, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/31.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1047, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/32.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1048, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/33.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1049, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/34.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1050, 35, 'https://cdn.black-clover.org/file/sugois/black-summoner/chapter-2-dark-spirit-knight-i/35.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1051, 37, 'https://1.bp.blogspot.com/7KVDH5KQjLQ47GudnwUIN7zn1qEDR6Sv4llDfMVlzCyWWKVlubQJaU5ZPsNFzHsfXnpdrPteoIcNp_Xwma3Bgr9R00i865copr0Tw3CpufiWxu9xgjYMM6aJI7bsOsvS-Cyus-ySrdo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1052, 37, 'https://1.bp.blogspot.com/eVB4JYllb2yb-I7KMb7XhkteJ3iq2j52RNKvm4aF-7OOVkpf2n816_js4gH4YwsBV1o84IJmAG-t3_Tz-J8lb4N6ew2bpH3YawML6J3rwihBinCW9drPSUDxNSwEXaakvYai8Llz58Y=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1053, 37, 'https://1.bp.blogspot.com/qv8zxZsWOHqMC_anMZb9fWBdwRfQIBCHi_KToIK8RA6oxUJdyt1xFN_DeoZzyp6pa6WLi_b-VGeTWXcrLDqxK_jqiQx4_ANhl-ji0PA_8w4Eqz_UZqSkR14XFMqfUG8CQIcMpUUeDkI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1054, 37, 'https://1.bp.blogspot.com/Kk7sSYjs35t0nRFDnvxmYhaxJ0zKIbmVw_SJCHSx76bR-z0i6uajNdGoGwhJduFRpzdUzZSZo7n4EYBOxL3E9P1jDpFwkbes4p1mH1ujveguFRfvbWMwxBvTJ_8b_sTepYwVhIZVpx0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1055, 37, 'https://1.bp.blogspot.com/9vvkvnocRY3Oz-PkEgm11pfWcZQAxdtO3gPjcNmKD7xeU-urmUyrps5nVO0l14i-OiJuKhHGU7rhqNu451a4zJK4HWV4w1TM4anAwVf--EKUQ_YUkcK1Mn96ctai6CN256PMcfbwvSk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1056, 37, 'https://1.bp.blogspot.com/kAw9nqFrQeXOZt8z2hqpTknDG9VSAV6tPvNHrHZrbxD00yOhlwW6hhUGXxp3rD1ajKBke73nJ_hSytUxGARvN_MSg9bSANRv0RhnNPz85-C4O26xNe6YV9mEMhXGkQKWxj34dhMa8AE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1057, 37, 'https://1.bp.blogspot.com/xCCqcAsmSbuMEdkQvzH4AKYsCtrFj3PaWWvhjQtLhy6_l8A3-zYTOfGz-onemJTHoRVp8vmWDUha1gNG0GXL0xqNC36lKs_x_flEJWv3JVKnal4S6JPaAUHUHHRmf7gJac8u8Y3mEHo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1058, 37, 'https://1.bp.blogspot.com/vVDCuU9LnNiNV9yAzR8MJ7HhyIj1uTHzMzjMHj0hR35QiTLpkifDFJGbbNwefpvHWvddb26ZxKJv81rM4tIlOzhhiaj7TZ61FK7XnaGKjtSZqrgzfB_r_ck-7y7Z9NUPht-hDZh6RH4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1059, 37, 'https://1.bp.blogspot.com/ZAsFvMTLn18bQCQnsLNdVXUMO7tVJhIWtJqm-oFoSfO8zA7tCiQPktVtqunbUHSPtOAw3_SzK-GdaxRJU0WfYLLJq1ITb5HDuqGwOdTKfylVE-4BtgS6YcIKosvzquP3HPmmcXjJBnI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1060, 37, 'https://1.bp.blogspot.com/wJP7Of8pJJC-eHhHkxjvJhvpR-3l6IQ_OqVE3uS1cDODtqXo1DQWokwLki1lW6-TynwqQsVhtc8zZ5sibFWYq5a0uKVR5d3C3Tyam81XBiJ7vyAh63h_cc9WK7t04v8_KW5yodQwOaE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1061, 37, 'https://1.bp.blogspot.com/cPivHCB13DcHT8iGXRx6BFzbVzqHTyMdHjd1WSnd0HRlnae0sD2JTPBn0JK9CpffoibdjbXb7VOCw5B0QIQfr8whHiSgOaMar5h9_3fmnizhRV6-2gAi_D0DxXWBnKMp5yT7LbPDTOM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1062, 37, 'https://1.bp.blogspot.com/CKJPZ2Wts2dlm6LREyl99gVLge2xkG_tvFzBNJ6HQg4QpcvRGAsZO9rVLZOKR3IA0ZRBYNs-Cvt24AChdmpyeGNA60-EWmZHTA82TTzlor5y-z_NUHd1IXAeFc8DiabOWzbXFjqnaT8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1063, 37, 'https://1.bp.blogspot.com/Yz0cVUsHLCAlvaKg_SgupZ7vQqK4D-Q4LqWYXW_sP2eSXJfai6iDrE_ifWtU9yffZX-zYodr4dwmdaBznK806Ue6Jk7Y2fsBLA5mz8SF8YwCtjcP34ndHxgK49vKDL-UVWOA8VGmaQg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1064, 37, 'https://1.bp.blogspot.com/roM07rLIc9tuzwRTzeaEFlphd-XphMT2GagRNu0vv-8deAnbyFgi8qdhArbWhb1sSy-vhmyS7DilNCWFaxsEcWh0eu5G7IjkOxRXQbl0ck3eMyJTjwthzNU1qdOkZT89BW-Nt8G5GVk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1065, 37, 'https://1.bp.blogspot.com/hyRLMQaQYqmCSO6zPuWAsBVTwhlKxnmDFMT03o06BmWw279sy-z9k4NMYoaGxjCT35iVIpo8lZvc7hInD6ueQlRvqQyismtyu7Kh75TJLlBobNIgTx6_KU81u5DwPb_V5k24zG2jx04=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1066, 37, 'https://1.bp.blogspot.com/6zRl8Si2035uXCUEMUh5v3jyp2mZ8ZFB_CrfU6oguL1xQBxK_uG_UTCqWsm_A68rnjzi3-Fogufv-MJkfZWvln2QswZzlUIRlGdvCkTYGKqgYHJRICMvpO0XnqKzkkpXi2n5wU474qU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1067, 37, 'https://1.bp.blogspot.com/Lf7cX_nqOR9JA3opOSEM5M8nbpiMMv6dHHCRftflglvUfnrtdFBE_dKduAAZ_GMuSJKVPZWuo2Q9N_SdpmUEllGd3IqS2K2S1TF3F8My31t60B-njHMAwfrO513bXW_-CeKlJg3g5l8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1068, 37, 'https://1.bp.blogspot.com/CRHpcryT_XAGA6cvsDp65-uYQbOWdGmauVHQ5h6kwlpBWpH-syPUV1nzXZ8NldtTc-j-eGXD3l64VMRXibzm8JdgUup7nijRjGUUswR7hXjqcKK_SC8nY7P3pfCgmYaRKVnCYVC4WEM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1069, 37, 'https://1.bp.blogspot.com/AMnrQAuzzna9BONpiuBh7MC2Ir0oTm1TonnNkgkLIVsptybAq_QDJtsVTTuOvpcZQcAxt6ThuiwAmmwlu6Bdm804OahS12ANDMmORmyw2fY7NJ74zI_jFiNhLdS841TkIDioOIWj4fw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1070, 37, 'https://1.bp.blogspot.com/cj4_shYiQJ8wXXRgjX4zyRlQrst4FCiWq4FDX8zHMyOGU6qfWtYZvJb9ZLkA5SV_jkm2nm5eRhA8Rc6p-JenlF4qhQH8axwAldmBae3p-XYoQeZyDV7Jmam9hvnkLIw59z6EJYVd1K0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1071, 37, 'https://1.bp.blogspot.com/6GGAxVvDro734tUk8swgXUOvu1PWfu_gHkMz1dxQq9EsxWrUhzy3SmzhlToNH0hH9F3iubapbP2GXQoqMZhAC-9OyzuOZfhfC0VwsdP2KVWktoGfYUp9P_X2E94hhX2ItQ7GT6RloVg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1072, 38, 'https://1.bp.blogspot.com/MTA3TvQlfbg5r9iVJbhClfCSuDFjn1uyrmcMzp4cLvCBOIUnJrzBYLm65oX2d7-RFEuBxZX6-0LXAKoKBVvq8dh2erg8H_2DA8WPM6W3X9b61Bmhpia78TExiKC4soDb-BI-suRan14=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1073, 38, 'https://1.bp.blogspot.com/FLl8FRTDmPj5JGMm_IWeXUtCOBTbxr1p2x_8EpdQ-e7q3qgSg1PSfcZo5PXm9juW_ziQD91semBa6zpsohJJ5lKZvyE19SZH4T2ANN8DH2r96UE9rLlETSbVtVd2vY9LoWl8WXgyUxs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1074, 38, 'https://1.bp.blogspot.com/gteergF6jnL7KfLt9q5_KrNW5nyiM438hH-8iVF-Hguh5G51mQqSNn52bsYjev5oCiqmanLgwnRLh9F2ulaFueY6KmtUTYySsE2va1q3zszTBeYB1v7zJ_0VwsJAySf1vu-m6DDnL-Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1075, 38, 'https://1.bp.blogspot.com/dTNfRgZrlb-a3RYHa9sQXookGdZRtD_9ZXErrsKhXC9vwfIGoT2TnJMTyU4U5GlxSXyAkjB0LMtAe99iabrXOL_BQUYdIGFmqtfp7AhX_eSjpVFHJXLC3Elqc_DArA0oSWD_jJYqeuA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1076, 38, 'https://1.bp.blogspot.com/F-ZcalgX9jO21G0_RVBszBZ99cA3VQF7l46Uc5icG4SoRuwc2mfEGIyX97ngoQJyucD0Pwf5SU1udUIMcuoXw8kIw-JA9inrrBc5A6InzJJzbE0L3zqQ783gv6QuA4Xkx5XbVlZ5IqA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1077, 38, 'https://1.bp.blogspot.com/RT5kZmyj8td2YUhC8SqARBIshxbmVI7YQG3YCK2p2-IGAtVqJFhAmjM37yD4qJv2z0cM85UMlz6AliuJ0tDqSkbiOiwRtDsgEVGJzyzv9J8C9pj-W24lcnrwEAtpksEkF6JWfbGwXbI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1078, 38, 'https://1.bp.blogspot.com/-G4Z2oeWJGNlRNOeayAVE0cM5I35oyRPCHC0tz2kXlgSE7hFpKX0CxcACqLWnVQ36zN3mGbjBaxuB8aAoyXOSPQ4jjNE1OYKqyRfqu0p5RLDsNWAKHN3CCWGtIKScS_wah0ZAqkx4Mo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1079, 38, 'https://1.bp.blogspot.com/Z0Zzv2gSmD-xSIKW-zVLVu4pE3VPhNyhDkcwBssM-jXzdiwnju-XY-CSRBZp_m3My_LqOTe8O1l7IQylcwgOvAxYH1B_8gyQ2QqnSB3KdjIQDHk47KIFk4C1-CyzdyOCWEaRr1h--Gc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1080, 38, 'https://1.bp.blogspot.com/ZhJJzDVh7RHLCSnd-hVT4tAoaXNnwqJaCCqIl3ccmH_9Oe4DFFwrKi0am2649yTdfV8TlpmEFudfrbyiri1kJqedYv6Ps3wt7TN_Z9ACrMPCuaMTX77-QzisCW3rkrNRzyoCycny4V8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1081, 38, 'https://1.bp.blogspot.com/AdmxBFujr-uvfUNHe9iqdSV-3vh-yWqZDv3K-eT-WOoWEmwo6jbEyOJk2sZS6ugnX_cYdpa3VUxUqvW2iGKQoKnNlNOtyjOwlgBGziAPVcPgEz4CcUAMrIu_EusDP4jLI4fbPfzR9t4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1082, 38, 'https://1.bp.blogspot.com/Bzd57RxsNnl0OZiTVgsF5eLjNeA19qT75IH1vFPSxRkEyaYwpe4TDsQqlRKiT7hxAcivfqpF9Hq0HAz63GPXX3RJS7R1MjqciiFrWdc5lWW71EyZMjuk5nN7m58asxvy6-fnOcxyQ6o=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1083, 38, 'https://1.bp.blogspot.com/7rlgmMmht_iBcYHE8fDONoliULSVTugj1xdSRCUCevz4-uxA1XfzeFz5WNXM85A9fhELsWTH8IsgPpYl3GeJ2tSUG8ovaVd-JTn9s0odyDrnzDawz1CDng33WFIEw7f9jHUZYbY3Kro=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1084, 38, 'https://1.bp.blogspot.com/CEQgKtl5ffV787Kod8ol42aLRAivpbD3Ibk_kz7vCJJFv-J_aONmuc3juHtIeWI5WaHfVaJaWGXLn8euahSZ8-_j5RcCKsfseLcaL2Wk0ACFUxRQikm36RrU1Ilo4E8WYEd9tbQZEYY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1085, 38, 'https://1.bp.blogspot.com/lOqa6T6Zo6EQEFvaamRSJRw7Cr1IJhsC0PIENRc7FiJp8X1BOH5Cpn_laUwYFgCcAGUDC-rMU9ksIX8Ieh0HzoyURiU5b3mQYAQESo7JUC_Q-nt-ElIas66O07bZ76U3R17BSukBWjE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1086, 38, 'https://1.bp.blogspot.com/FBp7WMVbyBetKPmUMZVPVyAM9hOVIy8Q4yvK2QLZZ12zXsQD4_lNwKDzfBpDvz0Mt4brwX6VYYp_zfyg9Gyh3eca-5KqvV1gGFsx_fC1FbHo85Wk2ICN00lw4tgViQFBn_QYRriaEOQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1087, 38, 'https://1.bp.blogspot.com/iu2D_0CmEltgZvyqH8ys_pAtFcyj9BK1PWo_RNAv-0KemQRHwbWIkJgioZ8JzHIcXlnzkxzd8I8qCH9DKR6qbo38qkLPxSdrhpmxTI-YqOdd5cYsYG4SzmbGLxBmiQQLsurAGA0MWTY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1088, 38, 'https://1.bp.blogspot.com/ojfMChlqu3zADIWF3MORyRHVo1q9iZBJ1Rc3JhnhtR2jedAHuvOJ7jAD69PMVd_tRtk-cAF1SEgTYH05Es8NWxIZD0F0ySsA74Q-sLO6lkEdvlakdWF3CAqFI2IcfrtUSs-bJqVWU2g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1089, 38, 'https://1.bp.blogspot.com/WurMbmlt2jmlHwIE_X1avdo9fnCfs0yQQoii9Q7taO-0DWRYQjCaxPj9jrWC7MMBcpaWvrF7NYuvX3lGIsIowMGTYrm8hAfdxUZusp44l6c-poCoNB_MFIMdse4t8Yc3oAerIvUttKA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1090, 38, 'https://1.bp.blogspot.com/UHpTPDtJM4pfjrVxRrIxY5PcMa0sdsOwdAqAqH_Mo81sYuIQkCRQHY-16QjTGX4tNsxmB1lETakAsuE5EPzvBVJUZWI6SydqOUWahgHxV5NiwrYZq184-z31D2P2xlSkpllrPySsBrE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1091, 38, 'https://1.bp.blogspot.com/in9CgL-82HWJ3pkdMQEvm13OfAXjM-AvWX1HKtD7djUt4a5lI7vRap6VwoJQzYQG-15LVirhq2UiQ14_tJ3LT0yQgwJxpJVHdgC6FI9EtW8aRPDqbIgFrH675TL_08hnqnifLQ5AaAU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1092, 38, 'https://1.bp.blogspot.com/jqRWwdHPH3fwK2xVGWdYdPxE-F8pmZTcsgneHopU8L1x6Dl2k-3xvfw2A1gPeNYtC2EPV71Ygc_vov6IHLePtv08XlJDMXPhadYhgtHeVzzLYlSJK5I9YStHACuwVbqR-_y1iFY1hNs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1093, 39, 'https://1.bp.blogspot.com/jtSTd9lahQR6bY4WmogDfbnqy-kor3CibNOC_DcHT2EtIS_WvxpBAynzRdPOV81GqsxH8fAeLtkzfcVReXSHZ2J0kQfdmSGz2rNidhTEeTp22C9BKWmQkG28rVjy6ANXAHhn5ptVhbY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1094, 39, 'https://1.bp.blogspot.com/eM1evj5hT_QUyfbRzpnux2u07VzEiPn8w3pqDUF5d28fC365ruzxdKr5fOGZ2N-ySAHHRyNhihckr5LVRAKGMtSEIEQRNX2hnjKypx1jwYoXejONZvJRj5I3amzWNY879QiU6Q3PKm0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1095, 39, 'https://1.bp.blogspot.com/fRh2RpOZv52jI6bZVL_x95o-HM6f_zLAD28XTqwkHt2Fe41M76x3Gu7wmkQuWw9RsyThqM8Un4qe0QvCu9OE5oSQmCCT_P0kGQ5PnLDZQx5Dekp6BhUto5M_AKcgdgYVhINmSf6kPQs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1096, 39, 'https://1.bp.blogspot.com/tOZZH6E9r5F99B-_rE2C7mGfQMhffyr7uOBpPOoxkouF1AbiypC8I1HhFA271HQYAyTfb-diVa_t4nCCWQZMVpWMJimaWygpDp3wb63AT3IdxSZSkMkKULMgDu5EEx09LLW4fbBlN6I=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1097, 39, 'https://1.bp.blogspot.com/mrXt0kLU-GFh2SEfFG10fNsx5EG3iFE6T_XYuuppiiAUZla3AuXiF-yEQfJHStwia1YfCEmcV0tFptnKukICy_WuJo_5uIoIUXV18065sby8xEZH13ShzWnMh1KSgJcCUvIr4KfA9nY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1098, 39, 'https://1.bp.blogspot.com/gqi0ReZnJCVQ4YNf-MXU0Dm0rgrzZ8gvQF-sxVBBa9I_JS7CHQKwbsddKMWY2QUkj04sXjYzhnEQ-JdqnsPrhHgg0D5Kjb8tMEuL-9x1wZE83g7bUbEJlsCm7Q6p6vEMl6ARbTBCXbM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1099, 39, 'https://1.bp.blogspot.com/5KrTutZfBvqJyWDWaMSBlT1ExxCSVBkzT0NVR3Hd9YN1Tfgz1qORmKkvgF7uD4Ipz7-XjqJzVS08xn61q-8kiwBF3_T47NShORAmITSqpR49MJVM6_CZAZOt0rJ-dbkNbsNv40myXVA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1100, 39, 'https://1.bp.blogspot.com/-OoCzDm_n71nNzwjzIZNRQgqPsZ0NVY6jgh25t1FVza-vA3t28bs4Swm-MZNCKLSxwVWcTIerjLdfA-wcrTb4c7YmVs2YXDOwvaS6Pv6nY3WprutymYo00tI6PT3TzTR9zuzrbD6E7g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1101, 39, 'https://1.bp.blogspot.com/PRPF32rTZQFS36DVyvb70VQOfbsS1qPYc6lwkgMS6aY2JlxFOaArIdIDieeMnFiXvo0hFk4mzRQ7LdW6eLBa1aHMpIc0dIgeZ-HbNsE-lBO2aPAQ857zcYnfKXXFbMEB6zj_H5dfQck=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1102, 39, 'https://1.bp.blogspot.com/X5N8r6VGEDTU0PBKndrcU81dt5SmVLJpbDApiy2skj0qcLlwbK6PAmDEpJskJgKpr-7ZXI9KoS8BJ5XOF-sHcePQcfOI2r1imKIZL1umpuDShKUvHJ9acjs4-qZzTQnP81mKq5RocRA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1103, 39, 'https://1.bp.blogspot.com/QWiqRWuubZ5CW56iEnRA5sNTSiCbAdFZ9SloTj3fti36EvQCqqxoLbwuxmmeI3PQ4xt1kW3SldWr7MN9xUJ0zHEP7tXwO0kUQ9aeyb5HygM0iaA9mmPx02-vmJmKn8ahflEtd9P4vUA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1104, 39, 'https://1.bp.blogspot.com/nu7QFgI_0uuBDjdFlYR1rt5rl4mhChOiQvp6hPqMBbP9fH8C7pId5YJIz9W3Awi-EdqiSJBPZco8LCF-csBPse_vPPbfM9Wcvtja2Vl0n2MXU5V83Znw0DT9BhT_pejw1VrkcjC78LE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1105, 39, 'https://1.bp.blogspot.com/KC4cpkV_C4kwYmoRG8oFKq9wTpszsMSXWJDH_0qrBVLQ9am7H4rbGwGBisqMBaf8jEXYh_0K-NIF-k_n0chMFBoK1C7YVp6s6K5QYPQAi1f1zVMPoqaNofgJDc611epDIl73zWaRCyc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1106, 39, 'https://1.bp.blogspot.com/E7BS9bRGPI5hEbON2BJhndigziogSKcDWKEyjz2a2PEJdcQGwZ6EBf--9MWdzQ5YojnY7C9rP_17ofdnlxJyN7zFJfn7PG5Q2tofADgckxAoXnc-w5kSKOA0FHveqojyBx4yLXkORoM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1107, 39, 'https://1.bp.blogspot.com/u_EVpC-XRHSWRENFuTdHYt8hnclOHoQioqrFw9h1kbh38HZR4sab0PgqjzVU4xubhTX97SC9uPsEy2f7NHmdG99HspN1Ltginq5gKoRIWHhhMQS2mS1SvL4UsuYI7pJ-uDZQDfue2sY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1108, 39, 'https://1.bp.blogspot.com/PXD1oOShiaA9BnDhNc277MiaZrPzPYVe-99YQtIgg1lGoPMuDHRrEbacG8mBbPF65kRR8AYJXU-sElwcvgjzXD8QJpwvZdAgmymxRDwqOtV7l-HRe4lZnh7J5RySdl4cwga_i2GIDNM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1109, 39, 'https://1.bp.blogspot.com/xVfxnhQX62HKa2Hc1Ts37BlyG_pqsf0yL5CjEOStcQF0GkqvN_pVbVOT6myEuzyjgy4T7dQKBWpdsggmtZ2ycZroQyZgRpfXVQ5az5sc0TGOKjeSzax3fMheeE9uWFxWkjHavugbCdI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1110, 39, 'https://1.bp.blogspot.com/0dpJuA2RpgpmzLEUYyH1ycIiwQsxRr7c06PfG6aEB8Fh8NNcuD_hgCrJWRJseWs4djugM2BVtZr6cg6LUELZx0dGuGR0nrAtYf9lC0V6543ha-4aiUTXu2AeKhgGdNolCJGPtKZIdYc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1111, 39, 'https://1.bp.blogspot.com/7QlWppz2RVEOIHWeIlzQAp7Woabl-ykPCQkErIaB-o4A_MtShk-5mcDFesCrhvN5U6HjX86TvoROLkOsVpcMC9uLVjH18qZL7u0MEjAtMQcEpzlSn1MCXP_YIE79Av__1F1dhbsxavA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1112, 39, 'https://1.bp.blogspot.com/Ud3h5MPioF3yfEm4Ap7drm15agIvvIQh-QPBjPVOyyNUfDKczfIXcTzmqiDl7L0dIGan-71kIcaTCXSF_rhgdXJ8up1GXuH52SOSj0pUitCAoqrvLJvtef_5v117RFep7iG6iywtrKA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1113, 39, 'https://1.bp.blogspot.com/NNmMskySix-vDs1APg-fffrDwdHtVPKL-jZ1PLq0YG0v-iQY5hoH5P-e9dQPpGCoS0Sk3McrRQZm8uRaAurXq2YQB7z3dGbZDahyAs8a64USCfIhXRFcg1x3_E_X8_dNz5bgm3nNtzY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1114, 40, 'https://1.bp.blogspot.com/7LSOIerQUbtRF5yD_YSmdvHkzvelxolDGZaRkHLzBVX3MRJQ-6gBWr7vcOW4_kev_8Hy01RpIq_p_PmvTjt8xBvy2VdymDkb2mtU5LWiP1DoU3ab0opp4VdfokdknZzC_NIBTIK5tQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1115, 40, 'https://1.bp.blogspot.com/0lwHDjOsqlB_6W5Z2QXSTHk3GcPfQTYJiNKO4k_RftDGO5JZT9h8CZXdajMTfYQiAt7zDijBW_L7OoHY-jzBIyNsOh4yqv-7i8ER4O78lh6nTl4idTyGPawzluycScD-phoEiD8a1g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1116, 40, 'https://1.bp.blogspot.com/xkaTFOhLqwD9qmKHok1UWP4U1q9GIcZIHQFLcjBwO-JzRLFhSNnbWKqtdIGRigcGcwUtAawFWz9xEACDsUsG4QNZZjqmdDF8OVV9-KVKftbLRlh4D5Tf-RWIYaCURYqLyh_F3CMBFA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1117, 40, 'https://1.bp.blogspot.com/DojKvm6Y1KOiel5RJQ19Moga7k6VGzB0jJAYye8r80-ia_2vato9VdvmGZIPOzZHftoCbr5reUemgGI6COgSH_6BK-MfgCRDvzCp_wmlcksQ9kzALczMuz-EOWI_o3XM6dJH8sB4sg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1118, 40, 'https://1.bp.blogspot.com/3AbBlhN9Sil4o5el1vThCRMMDtQvrkZ1FCNHcVUU_SUwww1E3Up3Xz4em-w1uvHV_uw4NinFuTLZMAFtcT8en1D_aonoox6RIfIbbPT1komxsspS4HYAIP-yhtKQbyJHwmBJrL9hxg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1119, 40, 'https://1.bp.blogspot.com/tU0kd4CRMQlIZebIt3LSwpIWB4NyfT-Wkt35vFS4igEFDbOjjDQdCkaP6vtHgylzSSSC6qJbl5GdhtCUnXKqoyhCGxiY9pZ-ejg3ZB57Cn4-FOjqlw6erelusJRDguMGQA5lDeXL-Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1120, 40, 'https://1.bp.blogspot.com/d63FczNXQasaDmmqLxkHGqN1n4mebf7mgKgMA3FgFBFTstyFQ4J5IbgXMOVmrhknVay9LUvSuMBkOnEdbKls1mzWSTmiLXfv7ziS-VeGRLgXXmojefFhthF4fQwhxyLrKyPoHDbBIg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1121, 40, 'https://1.bp.blogspot.com/igeXUk_V8Jogw5eLySm7AqFIWy0jcg4-ruJ1xs9NVHLSVHpAZKd1tYJgtTlFpiSdPxVYKfi4wJoEsOkTXr7bRXm-oS0y9GpaFAQNmrtX4tPV8s8mvVQnBRVEEPwSXzzijs_-S1T71g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1122, 40, 'https://1.bp.blogspot.com/wvTAOIPCo__w9AtQmBFVvH02PI9npVM3Zg5GR1wXHaJ0liZVKo2rThF5z_iU2bWAui1wblFLUCxOkeaiQhX9aaBo9kFQhdukDT6jTAxv21V5Fy2rZsMn0JSsvWZ04FTUobUX1HCIIg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1123, 40, 'https://1.bp.blogspot.com/KEnwSShewmcyWs5onY44r6E1hNEk9KUbq7fCHwbhLFmXtun1Fc0Q-u3Ur7Sj9fCFoGWCYeyQH4ShUjlr_l5Elh70ddjydJpcMIdrapY9p7yqrN3r1wK3iqYtEXJ_94llKT5PvrEyXQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1124, 40, 'https://1.bp.blogspot.com/NNVTfBMY4fHj0XctHDxACNMBCLjkqF31_qtFQNPabS3UwVHN5D9rnmpCCRpbZu9SppCAsHzGKAaxxp5EpL8j4zuyWaUmKDs20msHo3-rl8Ts1hUwDi16DF7d_U-ue_p8EbyeCW8uyA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1125, 40, 'https://1.bp.blogspot.com/3DYL1pC6gRCiusMTWhZvsosHLiDVwx5i-nQOg-7ZA-h-FHk5zaKFMWgJf717o-NxNhJFf6NzBbb_IU33LW0TnrxlQr5tHwj58XOyWzOwcUSWriLDFDhKDf6tzup7NnbYePa8Z5SITA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1126, 40, 'https://1.bp.blogspot.com/2GX74YOFhA0ZEMUzysQ8aKtXYJjFQOVhy6sjjViXn-NDhE7F0AmjTegq_QrdtY4LW9BZgDbMM_Unos74K8c5hu2HOnvIc-959BEQCw9sVqyIOa0HbSyFoTQdxrFsaama2lJV6g4xGQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1127, 40, 'https://1.bp.blogspot.com/ukfv1-JdsLO-sDW9lAnd1mZ6bJGBlZf0GqIMktfGWkQ4hJJFNLb5-99ZE6qiqsrMtytUPrN3oEl7Cq0zEpuPSw9CuRJu7nHeHDiN-9jiwks5FidAllHafkyJiiJY1v2yrYUBFr-_cw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1128, 40, 'https://1.bp.blogspot.com/FAP1Ex1zErdAeTlChCrFDcAXgZ2dHus5i5Uo3iTIAucjV1VwOsSAQ6AwdwJQwh5I9U6_2OTnprzL7RTSj9eBWxCbLLFLESS-wwUNFO8nNXMz9RWa_gEyuTHVZrOK8FWGbT3LX4jDwA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1129, 40, 'https://1.bp.blogspot.com/Gc2AVY-6wf08UAkIigCngy8A_HV1F4zMr9uoXv3KTWnZM5zUaI0FiIwvqp-7TpkGJfznQIlp4MltqnmttGjifj_T2fsmPv2e-34DpHws-mPVElUfrjJI2oAUwvwdOOBsq3xcl0KTWg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1130, 40, 'https://1.bp.blogspot.com/XQpu4S7Yr0dcC6DjqbBKVjJAow24Oi-pjansZz3tLTvwbq13qQAcsAVVsZmIZzXJVH-wKtbkXbmR-BdysoXez1r3NMXWf7sIlwTLmShvW-n_wDswYJZQz7NEsQQTUf1TTzLOuxs3fA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1131, 40, 'https://1.bp.blogspot.com/xLsI9CRZBNkltYXz3is8hu2x32P2PNsJBNd95FM6CDhlvqq0dfM2RpojdY-Wri80hMe3qQo3UkTv6ky46OmV3stbAWGtCp9XMXnLhJtbHHJhzH2Wo08kZqwknvgoT8C34w8r0RtdwA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1132, 40, 'https://1.bp.blogspot.com/h7ajXD3YPdiwxkox4fhk6T3J_OohzW2s6SAMgtwj7IbSG5iFhwQI8hW3fgJitzuGG7H3FSq1vik_ewlshRm85oMqTORd2nRn5wi-PZLIDf_xra1J8xwVy725968nr9eLxQw8UaFj-w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1133, 40, 'https://1.bp.blogspot.com/_bZdEcxJpxLsTGebk8S7wwX32dcgzhF2WPJfDtzDWnMfraKpuWuhlOG9QKV6QOMde5_ojomWsRHzQIjLvo6nBImiP7jOuxSPjsRKY4hbWFlJpplGavlP2Ag_jXAdlqFbVUvLZpe3uA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1134, 40, 'https://1.bp.blogspot.com/MG0HBkZOnzbJL905J0ZLoX04fI89dDPBpgBpTpUXJedju_Q5qEkc0ukjrwTMCuUOh9uYsPssFloA_WB8WfOLnr9Lknpk0BnwteNDYwHxnLSvpKrYFluJN1hc7HVty88R_MfIQllVag=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1135, 40, 'https://1.bp.blogspot.com/6JhwiH_g0iFwSkWpkX1MShDpLsq5ttHzZXP8f_f_nqUa38T-BFJgH5jfYOSeAMR659z-84T6x0rL7-mj_OrykymQOnaRIxdrOwA-PMEydzFQFgffW1TNsmfrH_0dnD6EspOCcODaYA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1136, 40, 'https://1.bp.blogspot.com/fZKtdPp9PFOtN6dE7cV3x9aycts3POZleFf5823htnzaAuJrAEkUNoEduCclvs50807136MRHofHBfHYG94zBuqcRKqaInZu6DgLQZKSzpEVO6_N36bVX9klAWXgAQrFTXMMFOR5Cw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1137, 40, 'https://1.bp.blogspot.com/G-wweGRyc91LIU2eU_wasxtzB86vaFeRluNpanDs0-8gjYtvGcxF1unDuC_YywtsHrpRHQOn0xBuMK8bGqWG9f5LPC6VYXLuIGKvbqhJQkQjHqeRIfmYeKIaPj1GgoeE43K-TjopBg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1138, 40, 'https://1.bp.blogspot.com/QmA4gktLZc21RaGxpfQF8ejy1mP7KQqnBclUWzMhQLv_DZOefRA5Now-PNrUdtMSaPgerkY7dJ5vtqzWLbDMzZ69AH0Ewlk3wv5tiNm4bfivPbRmht7RigaM-qAgZF6yc46rSuDn3A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1139, 40, 'https://1.bp.blogspot.com/lYSz3aPQFu-yt0OxKi2humzB2V0cuiA_6tCS9denC4dVtuG3Oqj-XtIHfnlU07u3XV3XjIU8xy65TOL0lmZkpBRHAwv-u-j3gZH4iyAI4hfHIva2Z5F9h0A_2wIAKUpAzBrWHjJ-FQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1140, 40, 'https://1.bp.blogspot.com/tx8MbsKgp8y98GEOPwt0JBCfBN6HPEIQrcYkiE-52TkYjlQu1FsPv_dIweExge_WOCeVdTFyyHCRxLsWP6J_xmEt-azcK4CDfHrlzuV2fE8AXQqosfACR-azzgF4I_MGGpBpYLgorg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1141, 40, 'https://1.bp.blogspot.com/cbOdNzTqBQq9q7pPWUUBMzPUofAZP8KpTWV-nUMXBtu_EEQsX02z2NIHfYoJObuLewZLNkK3vnPXrk2jLqgNF_nFcFl73w7hgdjbO0eTFJfIsZ5qDtyL9KP5NjfHEqNdccOgTXyGtw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1142, 40, 'https://1.bp.blogspot.com/Y_t9dRLK9ssuceAdv2Vb57eAGggSdZjNl2IvDbksEAFrhpRZQ4pt6RHPUzSvHKg5Qs5XjONgHPFweMgyl7TJJkDxuucpBZxfICRQ96zQDpsu5WDPOYIZqzt-YWR_-gmxUN374SLcvw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1143, 40, 'https://1.bp.blogspot.com/qNqtpVgK2QItZ98PE9A2nIECzSJ_4LzzcJQlEu9CnzGnW-UCEdgf7ltziB4edImDvKc07gSI82Dt9gfZtR2N0YtvR_nKGmjRsUhOxjKe6cRvoltUGUH9Z0ePl1YLhpIE2Y6PII3FqA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1144, 40, 'https://1.bp.blogspot.com/pPQJtl9cWAO7B313Rxf6Jxy-rPQU8BpZopFM-xvV001JYA_Hzj9emiAVSczZqfBCwjiQgt_ru35Y2nXGfranHbvVnS5wAfvHPrmuOB5Pg5Zm4bA8GlZspA6jZco9Q5vSuoA6RJpYtg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1145, 40, 'https://1.bp.blogspot.com/qSJ1Bln_eBG1zzongS44-6IhMyLlWhuYQXg1RcfTlo3uXlmW4DMhM8u1rX2k8BDqBs1ObVwkOvcJaRhj5Yw4fz_w3SeEUPcZN1PvqCNqGm_JKkBKpz3snhCblVWXR3F_rule3StayQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1146, 40, 'https://1.bp.blogspot.com/vhIj-Tq4qyp14o2MIqRQb0en69LzR0iNYjaDXUzogWSOAJROlA8vvczHH1DqaQzu0632EAfV9N5D0GrpX4RxvIdtjDYiYP7dJqnI2Av80y_KAa82OUW_-mWxupB3inOT2LpdA4PAZQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1147, 40, 'https://1.bp.blogspot.com/woWWzMjfsSIM0uiCeWhbx8zmfOuTSop0Gg04cv3sFYqV_4vO3gKLEeOnNucWai9nwmeDNr6_PZnN1oiBrG0kfHZc9VfFuXuY20eggb5CDIFK1tM8VTNBheCT_KIgRnM2zJIpg_Gdhw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1148, 40, 'https://1.bp.blogspot.com/Zvif_fFGtWj_N-3mxM0830nqwqJ1nC5o5kUcYpXrsTz1Q4Iqt_oIj1ClFvcvVmb9ACSfhUL2hIZDPB623GRjB4qIE3RtZUjTfGV37uLy3KRhNlW2kaLEI2eZk5unSgB3PESROVromA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1149, 40, 'https://1.bp.blogspot.com/YJGnwDmQz6xiVClbGaYKSs_PtaRrFd6_KOlLyar83XJR6YKCYrt4DUWNc6jDLrox2A3l4GTW5EWCxQOuPgpPrtGmIpRCW1--XvvNOTB0K48cMC89T-AmmwIwUL-YP4kJA9QjBKpP2w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1150, 40, 'https://1.bp.blogspot.com/7OHEyTlbPnlpLO1lRc8ErFM2sMEnKYq4QDELj8b0sCLzIc2yvTTZeIDOYVeH2RZazwyYlHSuZpwg1FIqn_ZTw0qst7COy3SGu5UvGxagjzTOeYk0HfuicYPmPizSwsCAhR9N2vbAxA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1151, 40, 'https://1.bp.blogspot.com/T2J9eDCK1JuMjqKchfnJVmiZQeSjv4gYFrOg0wXa1PYCIpGd7Wo2yjq_tmEvLuO37VpUVbh01ZblzusDr5EONOfgbcAaGsDlPfCiAy02tQP2Es2CKntR5i8MuHqZZtKGnPcSWle06g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1152, 40, 'https://1.bp.blogspot.com/y9MLR8QZx3nkNEdE7RoJ-UjiZs7x1kwiA6IIHgkdBJq2DyQ6Gsd6WucCuUYAsGRC1wFs2AEo-wGVlg3f6UL8_exgdoLif1AzwOLkhFE-MY4S8y_uqfzttA5XPgkLeIvTGxyxawhe2A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1153, 40, 'https://1.bp.blogspot.com/rwSeApSNGEjbw4nnDYGroMKtQ5NzvGtwjrpRVKnsfz1UiLLCss-JXwUq24Euxn--irZXmjj3AVCN4Zh4TTQVGYVWmFErkxmgeCFKHFTsW5QI2W6oFq7g2JISfdBifJDPFKUYHqwooA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1154, 40, 'https://1.bp.blogspot.com/V_cApD-cDF1NFAY-C-4_R1JfvSxeQuFzJfb2GDIwFbPRGZKx--tI4bvDZKZQ-HqxKnMPMKdjbzkCu7GJ_4ZIok-vrNn-opKKG6zDZq793dNI9yvZCGJlDfAG5W_sTRlkLa3YtwLN8g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1155, 40, 'https://1.bp.blogspot.com/8iVuMR95Q6vdzbLkttIuoD6Nc9ZmX-RqWQg_W4s2PcYfK_O_8S-MG6A97eGubnBx5YuQl5kgpAXhGiBrevokEpaH2bFqIzG4WIK13kRR8KGL8nBoZaXL8nwqlU_zHBEsHCi1w0YwLg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1156, 40, 'https://1.bp.blogspot.com/J-ct4ePXb_1fPT__qGp8Gcvo4RtJ2BhJb1UHWz8InWb5Pfk73Zzu8m5mfvE489M_5DPFpDAE2J64wxW7oMaebb43iF6PeESe2kelcy-CO4-lQV_JAofBPt-NAh3j7ubM95HygrUQ2Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1157, 40, 'https://1.bp.blogspot.com/jQl3-e72KA0G4DBO7xjKbcNpIcUT6ONHF4pJC9_ZrbqLmcKhX7dpFCbnk7UFWGaFmk6DJFJXCB_bnOQOI9G5atzxSMHnc3HtlL8e5J9_pjy3ToQPA8BYtdn3CKH_1WKpjUyJPFaBng=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1158, 40, 'https://1.bp.blogspot.com/-VSMMsyq3tcutFL99UUtb79mxWTQ4x4tkTQYuYVLiRCGDuILnUlrAyu03ToBkvpBBpWGiWj61lXwtEU03fLb_zH-Ipm7PrNLz_LHt4rQX2k1CfIOyuSEKR_ZeDqmnIa7VboSrbprOg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1159, 40, 'https://1.bp.blogspot.com/KFAs7K29ZF7cN-IBrbzeLH0K_rOZBH8XV-m8ZN22jwUrErP8Wxt9lEH7txKYX_whSmbavTeliWtVQLHRjMyu1XZtgEQKQtJGOOXFlyp5gCrm4umvbqx2kWQhx8PZjN6kPBpgnSLpXA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1160, 40, 'https://1.bp.blogspot.com/SV3hs7vzKked7lUyHsGQvytuvXoVfVvl3rufae70ViiHMID_fsqb7lBvLLoecOULkTG3N70WQoWm9ksZsBuciJLz86G38q5_gPajfjrE6foFpiMLJyYAfgCzsKDK8Jjv_Hfzu7uq4Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1161, 40, 'https://1.bp.blogspot.com/AwePNuPbLcICeg3Td0VLEEq5pLXPy3jUAjZsFw3aWfJk--_TNakMd50Iox1XqAxFVlf-IdigX7Ck2X3ZegcurGclsi4wu3ygF00I4pzbzAGyjee6hacFQXmetby2TUDWajqPmxEuGg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1162, 40, 'https://1.bp.blogspot.com/3EMcDDng0oG5FloCiXLbynzcFFDo018aV2lf5c25fzqFyMZPkpmjV6GyLHk1SUQEjpS-1ukFB8gNb28J936zw7OMWWQrgsxnGxgIFkNj0OXpyJ6Vc9vdzOjktGvdrt6ulVzGbZpL7A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1163, 40, 'https://1.bp.blogspot.com/bWCMwNKJK1g5JlxSypavuqHswBi0-FObIVkw3UxLPxouJuLPBK_AOvwDbDVN29zWFtqTMf8nWjOZ3u89NRPoeRzdee3rtZWj-vu0jXgFZK8cbq_5g94lLfSqNrg4KbuPGzxIG9OopA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1164, 40, 'https://1.bp.blogspot.com/DngchpEAeILwxya9feKL5SHC2fISQaj30ZYcgVesB7m1qP-5Wh87Yt_teZdPRgP_oPF2ydrIcv2ZqQZEZvsjj2MkVEmy1KNRiDzezilCQ4mwDgplZBM_Ce_9_AEDaO3cUvJh_bmCBQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1165, 40, 'https://1.bp.blogspot.com/4l4BXsMQjX5C-86nF2NJl5TjaMreTYhOzkY5HItsYgIibWvYtZ3ltX6aP1_OlXUI-C60uwLZWcOLHfYCkRAqWWEKSgGtRR9xtlUbLQOpugl3kKFwzjVpZMuhjt4EYPtr1AljKA955Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1166, 40, 'https://1.bp.blogspot.com/dN60u7pj7xBp34LGAWPdd26DykpMluXCU95KrNHowIyedvjqlNcbo4CL8Ix9L1oLzkt9YDBVUxoBenRvgfiOeriXK8kGnyveM0egSGT6GkWZ8wLD_23-5QfmTLYdUEXLa6o3ah4-cQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1167, 40, 'https://1.bp.blogspot.com/_2QdBlwySb_0yopqubtSVAM1D_Dg0fL2K87JKNM45NaLKNzvngsEmYVixz-nAZeROL7YI7Gq635QI6p2hno96eRvQbAiHbub_mjJ7MaDoUTugyR6AzHVNzAlNV6ZIIduFoEOBLkLVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1168, 40, 'https://1.bp.blogspot.com/wr-1t15c6jL_GRCCfQ74VIA3SMZyuD1oojjQC6nJY6iRvFUxHOucNVeP276aAASvLie7MrRnXAYU98swmQPdx3dyWhaOHQC-MgkkIHU6sC1B1hMFc_wnMzZ6t4Pci1wu2Um6Fx3-jA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1169, 40, 'https://1.bp.blogspot.com/o_3qHtYXIFMvc2apnQAo1J65bj3Ivs-cKV8MEtO3gvzqFR054vjcR0MyXyFr2eqFV2cce5e91NgWQU2_ivyd4Pm1xo0H7vQ8YADKrMY-EmkF34q4c1WtTVbkp0XnFZumsWrPaFIc-w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1170, 40, 'https://1.bp.blogspot.com/sRX148w6mwzv4iZw7Y2AEpvW1NAzibXZGjz244___M4XpReAs2IlsacdrIRe-cA5mcTREqNiqpeJwVaQE0KLeLEQJRJJtZXEpvOlXGxqaeZ7tJCvuuz8KcjYdATCuo7-gJOw9I_3NQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1171, 40, 'https://1.bp.blogspot.com/GccdGMyxcQxbq3pXGS9WAe-g7wL-5s4DDYjRMJVI4xub1tnwawEZdSiepFaii3ScIwQDzkWtnFGcVaSLV8fQmRoZM5I5RAs1uvcqK9-7lccA3XSw3dp6DiJEOl5zw_pFrQReSL4Dyg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1172, 40, 'https://1.bp.blogspot.com/qWBp_U--pwM3m7htwpyu1hyt26Cb8VWMoLzGSeJSXL3P_-ji7uW696zQqjGFEexD2jmSohuFwMbfNf3OWnQ5LN_0-FAcYi7_A0aZc6_PQkcritf5OuaQAjxMKWx3AkEc5LCDDx4Xhw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1173, 40, 'https://1.bp.blogspot.com/3lyxJRDEjK9oMDZy2YomZPPJb8UD0Fh3XKNitTfb1iQyqxPpuL38Sj6rmdwhimyzIEkysWKjeXpCdA6RjpVfA8hf49tjiNqtSnzbgBK_rFpNzCdogdUDK20PPQ-k9quv6Hnl0cGA_w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1174, 40, 'https://1.bp.blogspot.com/jlHFjAJDaBDFHNozk8Tor2O-Ttsfg-tYcsRTElyuMznrLt2I7jJoCIwb9Zpgt3TGLk8IIh95n5tXlscpnRQJhFUiAzwOGL2lMQQQqmXUP9-_HXeh0iCqKLk_HIa8ajsekuTevnMeFg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1175, 40, 'https://1.bp.blogspot.com/mSEAUsmyoY9zqyfxLYz5uZQyxY_1Yaf8MGj9ScKgF8IAnAxK0TI-alLTYdqSH3URkhWWdeoBpL_snRQ660hK0SF1tnqpXItqN92eoOVcfW7QOVbJdd8M91KR4wlN6qR7Gt1wLTu4fw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1176, 40, 'https://1.bp.blogspot.com/0HEQlxy_P0OQDF294G4WnRt8cVS51XQvb-__zsSM8X-GUIT8lMpCV2GxMptYF212os8AU-h8kd_QZScJl0og6vWpZEPYVSU2Woo8SH7XISiINjh99HLglV3ryT13_aEHqPpeMhQJvw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1177, 40, 'https://1.bp.blogspot.com/987Ddbfm-nZXf4Hdf62NXixkk_kl1sZWN7qKe5hfS9CrdfSuYtlKvC-Y4T4TVsHkVqyUt6hFo33_kkOI6szI_i3YYKqOhqJahverQJBnysPS1_mPMlr7XSB07knrGuKe6-ioLYpu6A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1178, 40, 'https://1.bp.blogspot.com/Nxflxwas0HOACD8mU0tXUxAhJgmI58FFp3dZBE_ZqunNk4dCPEALA1rlslB43E-vuK56HLQe0M87C38e-s5F0D_Qso9sAcpqD3h1_7qr5dcUvhFRJx2yCl-nkwlOu97hsE4bKO-bdg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1179, 40, 'https://1.bp.blogspot.com/04CcG-90Di_UIDp9ANPFQmaFoZLYwX_ET4MZT6dH1yXofBltgh7Ev-575va5Mf-EXYO1Ue9IKuDYdWK7TboUCvm7x6YDp9gkwa-IbzLz9YuDQlHux_DNgNymJqJQtlSEEtPkaC4Tow=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1180, 40, 'https://1.bp.blogspot.com/nALJFktDor93eh80CPhTCv0crT222oNBtHc5v7ZQ95BiafrFHBXKNOf8LUtobYRMIVp6ilNmivafNxhCL0iOANvbztiSodvawTNEO2lmse4mSwMk5nKqV7D6gUjbmECcQNe-Te8M6g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1181, 40, 'https://1.bp.blogspot.com/C8eJpVK5aR8W2hAD_nyATRJnIs5-6Uw8OoiCLb-yugawMCLUeHLZMVeys6ysLvVSiqTTduWJxLyF06Clkt7H03lkO-brwVLkHUQgB-DJoDmPYjxl1QbaIzb8F6C6ZX4GIMC04xYWUw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1182, 40, 'https://1.bp.blogspot.com/ddywk7z2yKoSoSIHSv_VFUTAMEFpEFQZpn1vHoiCmipZwIGwm3h8UXOEm0XNUTo_lna30xlSQ8jjsc9_lm_qAXvRx5eF9pjEjG-7JTpz2q-j-m3ulqLCqQrQq549NwB3ffJViIdVNA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1183, 40, 'https://1.bp.blogspot.com/RbdskkwgZuVaFT_Uq9G0oZvDikp1lYsYnFoAj24tW6u-WYAIeo_1xcFY6gvwRorfy66-wosShWI7k9b09xXTO5TiNAqum5O3dgZRcbU85OR4MfULq4onPPSzKK3xeluXnZB_WKPYZQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1184, 40, 'https://1.bp.blogspot.com/gN-WB11BnDVh2iUbgfZGRlds36OsvIvnN4_hryFBYr5poTUtm8fJV56lgnsS5BsxlCRKl5IetIUO6hVagTk8rwHF-k9YVkU5A00VcPRDrBYXyE5tfdlAq_wsvA8MdKi7Nbz8BIVGmA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1185, 40, 'https://1.bp.blogspot.com/dLL9ol4wbsyEl3HA1HPVOTehMdRSqAJm8bn_7ai8GiDgmZrfLr3YzDpeHJb_3b5ux75oFUmFZhUoT1Kxy21FjE01JVijAG3fCBGWQ5sF5PslmZwmGbN307Mob6TwFQH9tLAZZunpRQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1186, 40, 'https://1.bp.blogspot.com/pJe1OE1Kz35Y-TND8FG5z8QfeA0p16Jp2LEQCCl_iUacbL4EsCgKfrWjsBie4xSjRCYufzuXUF34uvbjfMV1FCmcouziaoTRyIbTCwqBLD9UxVHTSlSRcbTWTmXI6Sa2Lu1Ig1WNVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1187, 40, 'https://1.bp.blogspot.com/GLBRBmrlu52MX6WVnu_cj9cmkVb1uydI2ZBW4VztpZSQfnLSRenwIwnx-_1R2AbK644gkm4ffTzkPtUra86AAdfmgFylDH7gfV-S8LLxSC00V8j7puo_k_GBJOIzfhJ7isil4Zm9Yw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1188, 40, 'https://1.bp.blogspot.com/oxLYwXIcCpzOabAAIjG4vq90vbwCKidHcGMSF_49juxDYENCLsKL-ai9beqQmSh1m_AxI_YcvpGlfCHcen4OnAJyiNAYyI6joLz2qdcDciJS-L6h0_qkoFaLRtaDpKl9I_k1QUZDhA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1189, 40, 'https://1.bp.blogspot.com/7yN_aHm7NoNRXzFe_Cw-P_0KU88xfL25NXuN1ARErGVwtRy9l0PKaqOWbKrr-rSsvL6WPSIoOqQPSbSl9dtN_zzy1xD14ml_hf6Y7Kelr09oT_HdmMXCIAJ_ZGTuQqooDV0KKikZyA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1190, 40, 'https://1.bp.blogspot.com/A3D0R3hgb_2IN5ZhTx25r_tvCQTytctN8PO8T_QUhNIb4GvYyZmgy4YjWay9OH5Fq1DUwQkaWXsRaIfQAhKsQVSt0uJ8XeVHY9qXw_1UmnaeckrIPiWKuJyE-afYMYkt7t2jwYeShQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1191, 40, 'https://1.bp.blogspot.com/SU-hecO810wme5PdEclJIHjK-dtdq9rx0kfGRgwfQmKTd0IXZbDWJrPfjnRoA7DY7MV0DwUFeb1i8iqW6405O5I8ihKxBRq2MKs4PjH0F4l2VeoFxHJI5qLd03B88hnPZ-_hfnhjAg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1192, 40, 'https://1.bp.blogspot.com/apOwtVt8oRjKh2QI3zv4niVgGqXpDWG4yrUSr9SymAD6nwZvkangMWsA5jCl-Vv_FuwaaBUZalgHIVdZY3x5ASgf4uI0f6hNdYtmzfiE4lypaP693hbMV4OjuMSr8OXlPlrI4FCkrQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1193, 40, 'https://1.bp.blogspot.com/peElgMeWNia0lPfxxHmCcnZWsK7R00vC14TfGle5EDM0VtRVr98TMVH39lv-olnjwpOHYb7vyG9FU59OpX8MC115fFmw0XiEaLE_ZrGoneGnZ5lXiFe6ZCNaWRi4mUIrUYWTT_7a2w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1194, 40, 'https://1.bp.blogspot.com/QcTDNftWfYUhy6jiIw09sznnMbPNhKKKSlNx-LhONMaKXwafY99ViqZtaJnTq5j3y1q-79DkdW4YWoiSYZR59jzR11x1jV0Zped16S5d_lskAEDD_ur-b4uxshniH7JqhBlN1gEnzQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1195, 40, 'https://1.bp.blogspot.com/GelpjjvnUAF29dt5swPhL3Bl-NjQhMKdPymy9xQepqONyG0WKAaxWvJAzVittDEeXrY1NU3ZE7vAdkpykCnprdwgz_EXEmkTJQoVxCQxkmV4Xo_QoPDh2AKCJu6XCeTjGiKZXLn7Vg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1196, 40, 'https://1.bp.blogspot.com/wIemmPmH-WVABRTyKIyvTBjYwZAlpiaWXWRDSebY615T7nGXzifHy9VcYha_9GlotRCOGmfmYbGWlgbOFXhHj1dlLUu5nKn3FMlrcAtNKqYsxgQTbZQooB88PA8yn_xFdqoMR8jFsQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1197, 40, 'https://1.bp.blogspot.com/nMe6bQ1Jl48R2hXEPWWy8BGsFTJd0iC8N_SaEp2x1Du4m1FQKSBCy3ElkS5C6fuqEUftAiFptlQUVqN0Ts_4LQ0MXtMQk1BOBkO7CAf6-dA6SyaKR0G2B5v5wfw9iLuu7E_yXqY-Eg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1198, 40, 'https://1.bp.blogspot.com/213vl3Sg2zVxGAnyXMseqPZ5BPR-B6uo-8yZk4y5lA1kANBH94WTDIvCA02lWOqVaafLa2EJ0MKZZJJ8uHrrV8G1qu09DKxxcqsq0PVgqsBOyy2drzEtGNe-BREJXGCR7bE5oh0kmw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1199, 40, 'https://1.bp.blogspot.com/ycaHhD-vdiXWDY5ckeCZU3f4LBX_2Yjj9LKyQwmGBKSBShtoo8C9itfSNXLG9MRFkF9vfPB7axbE2W9dgEJseF3R-CQt-Y_6geuZwNAd2e_GnVUOzJdT7DPKQ8bhN_WOqdWNj5ahjQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1200, 40, 'https://1.bp.blogspot.com/6u4WC9DGroA5t4zb_wYvueG9ufTgsxnKYvcHOpexcEAqkSvqyOu40p8RWp3vuQGKswKvNXQl9Gwreagx4cM6PyQlj7XhCM2Kk3gMIBcA63g5GYmlwTB84Iowm-k4gJ4ffjigPMnZtw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1201, 40, 'https://1.bp.blogspot.com/PiUCVfOhJ9qY1-FeuPGcI40j4_Rza7OlQmefdy732A3PFxYgKC3TtZg6b8gYr-3gK07TVmh410TMKXpUh8bKuU1yMC00b1T0xC9ZVKNKfNaz0jViiqTVTKSmXPvPeoOOZg_4aaWeFQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1202, 40, 'https://1.bp.blogspot.com/wmXhqrkymG8E91EWf0_Ub9ljPb9D1urBEyCLAYc-ac3yVCJfSyMcQgNb68ufrul9RcapbfkmQe_zCVUlmN-G_gX7cc1wgN5tgOd6P4MMPikBY1tiOZ_quI36Own4YA6KjejCslFyxA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1203, 40, 'https://1.bp.blogspot.com/8BNlzCO78sCjLtVMNZ-foVNkgD0OL3mtO8XsGdDofD_KdXClfQr1HnCBug59AplpKONVqKy_lWtkn8UqYdhyEaHnXFCQsxA_sJ3L_jaLFfW8v3jTLjIytrlqmOK3L1rFwRePTGR4rQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1204, 40, 'https://1.bp.blogspot.com/b5VdnTjQBSxcs89JlDmk7pnNs1ZPPqpJ5G1rPwDlYdQIha-6dzvnqAoz-ki27zi-tXhuYreFjU9bD1BDXgyAio9ggcmwErwBI8iTUay0lpXR-hdXYL_-KuOnArZ4LMin0umSoEsLSw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1205, 40, 'https://1.bp.blogspot.com/WvuVMKuo3yHHcVYyo4CID_z-7FpDBbNdgo1oUVkeZAvrZgC1K9Ui7QKWJERaF8lTrDuro-3BW_gf9lvQWl2ctOqDN00mY82D1o3ndDD1-bTQ7L4Nsf8BweaSx1tlGrltOeJ4Xe5svw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1206, 40, 'https://1.bp.blogspot.com/pV7J13SPNmt_4b9HqyD-DrJ13wnfwZwTV8Gavk44hrUSFF7qGnCA57bfwtNFUXiXUoPn4ipj-218MF8tmAuKvATrsAvAlmR_rn9ikH0l3UCd9FbS8EjLL4ZZiGBKH76YCc8-_M7yZA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1207, 40, 'https://1.bp.blogspot.com/tmlnoEhMjMbIU0vJPakDd4YbRZ1ysYOIhYjoA4eMwNAKdUZXdY22Q99s_XUAb_VrC5X8uLEGGniuBMK5yGn-g69wyu9B6eoprcg1HXOXSiEaeAfF1mLNvMQjvWlHQOEzwvkPr0_WmA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1208, 40, 'https://1.bp.blogspot.com/6n-1Ni5ZbvbmvYnDGLC2zEn1upqiusuj4IDMLPA7bI6ZBWN5wY0CIWosxSQ_ympduorstKxPCJ_LpaAn8Apq7y-MG0F_fGqrcCjDXv7AlvJ12X0cYpuWgjznegZdgdGeuzbg6d6TEA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1209, 40, 'https://1.bp.blogspot.com/-MghabdWznNUnlQRAH8bvKlZ3IjjfSvweDkPD4J_iwT0nPxWZyFPLT9RTF5wfmX9nItmq_H2abuTi8qtEwP_ynRQUx2zuMfTAAOU09tL-RpErchoXqdjcCHu3Ee3rv0nWzc_Z0ne0Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1210, 40, 'https://1.bp.blogspot.com/TeVWw32X3NN6O123OzSYv6ZZjGwys2m_YGGmUWSBpnMwKbAiArH_3CZJ0scYcA0OjoNYUC1yficEgn6X93R3Sc6URHTPj4VdDsNYAIQIStUJFYoW8Es67smrrLZruN2xMuzLNpfrrg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1211, 40, 'https://1.bp.blogspot.com/URagoP0JHm9nEOwQ_5YGwG0Y8mJNy7StTuR9JiMS_ZICrqRsMcAu2AmLL4Oy7GJdtF6w17-rX0-CtgZX7wAfLjgvmyoGpLrH6RsiTBvnqfvpOxDpbWdtPD_mPS8H9aLTya9h8nL9vw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1212, 40, 'https://1.bp.blogspot.com/L4_CFPw2v31OsN9gSfA2FFRLtk-F5rJS3IvkiZILHBt3suSVNR1NhisLtc1C3jnixZTMseFJaEzCHSZ4Z6IY9K2CRMoLxq0I13bMHaskq9DfVKa9GnITZ7Z0QN3kissMSFyoEsoMnA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1213, 40, 'https://1.bp.blogspot.com/JeHlHugK8yzBBZw57CAmwmo6BQq-0MHS_6FCrZCErMRiTzuOB1AWhmLKQ4atB7hSGA13rMk-exugWx8aulY7hkyf38FgWMXIbATHUxHE1BlOPRar4MUgIcCoYlw6LhUc4nfr1amGYQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1214, 40, 'https://1.bp.blogspot.com/hLjZE4rfJf_5f9UHQ2GEuNXDVE2e6oxcV6bRDbR5jLTlXNSGmpGXdjCix32qCnTR-_Oud9PaOk6cPSxCf_bPqXJtJ7SUFXgedw0Onop8csLQruJE57wUWTslksUEIx5t3V3pnH2X0w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1215, 40, 'https://1.bp.blogspot.com/tu31XBdutsBfnTaKW9Do7z0lESMUGVFZ13W1Ed8HKmwRdKyRx0aeef3ZvtmTOb4ytf7-1O9bOGig565d7FJdoSTH6QQC3sUErMn-bjlwo6giFvRWly7Zw8wsQf_n2a7VJGOlFmSfMw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1216, 40, 'https://1.bp.blogspot.com/5vSDfWTbiG9rpF_6RA38rLO3YdATUeFF8ltqxQheZKskDEgUd95dr9-WRL5DP11t879VmoNUi3CBOgLg5gFBgFH-NL3AmTI5-MGMMp02S7-OCL3Tnce0KU2pFDvoIpIIZgok8xfrYw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1217, 40, 'https://1.bp.blogspot.com/3qajUyRSQeXnzcwoGhtt-ukO61U3yh1tJarLOQBIrwOPPkW8Gv9SFGZGVKgfW2NrLab6XhRovsroJ82o4Sa8gm0cKLL6z55nUH5qOptngOgkTtfmq9-ZxdlQukq4qMEwSlbm4Z7FIw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1218, 40, 'https://1.bp.blogspot.com/ldlCacSWrh8QstHM_wkQTabk0QY5IK3K_o_W6CfbYOgS_Pu5HOMNoI8YnfD5W501PN6SMRijz40Nqn0au10AY4NcMjO7RRnmILIjBN6v0PoYpJZlfGfbUSLYPU8wkHvfzDaGncsrLA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1219, 40, 'https://1.bp.blogspot.com/G1gj7h04Kq1WyhkHXopvTdmX9WXe6xnG8L2R2I9wRscAIjJW5lDnSYqnNDjgDhIOm_sICnUjQxzIyXfMpLmzm-SX8aR9p8PZ0Yyu0UK_maxRsm0oU89TWCCn53wpfYnh2vW5fmbwWA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1220, 40, 'https://1.bp.blogspot.com/ZkNEWICYWFNFRYX9d1kn55p-Z77aep3016WSw0QVjx3NQZBAmOg3DHv9MtvN72SttCD95E_vWYHkB8JK3To0yF6C15uoJ6U_GWz3zX395_lbHfcVwMYBrOA2yJvwOrVYrAUc_yYk9g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1221, 40, 'https://1.bp.blogspot.com/cumlGiLvPaGLXxK7joFTBahiEIOt-HJ-MJxY6zSJ19P5QDMuafXtNeTZOHZeGUHu0duH8YWfnvEbQpePR2PenO8y5gvnRvvAS4G-BRyw9oqt2-HWCuJwkk0oK_ucGjCMq1s1IFgktw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1222, 40, 'https://1.bp.blogspot.com/_5E5lZjelt5G1A4nqn0VceLef1QyQS9qhXWmQBnyzIKVMVg62YBlsw6BqVNPeMS0UfyvG4y_SEhG8QhpqlB-FE83EpMzSNHxyZvVIoh0Z50zV3QAf5XgVG74MhcPRFz8u9Wdmu-k3Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1223, 40, 'https://1.bp.blogspot.com/5lX7q3ShBb9dZaySVmq7HZnf07btKGEKHOCVYeCMuZqN2Ne4PpAbyvybBoD7PtFS4zWGfwf9KqO1yMzVDiklgJG9tnuutVXi6LFBJubl4gkk14JvJuk8ONY0E311IyN5ekrkgfhxGg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1224, 40, 'https://1.bp.blogspot.com/0J-SPxK-ocz1ksvYjDJRMP5hyTlcnoED6xdAE-dYj7hRC3SdNhqk_D_q3NBaPMms9pNMHjqJUis2-ECe3aRs0Ko926bdzp3A8YzoUsqKtK0IwmTabRLQiKMx04kmAvFLmJ9no6ZU5A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1225, 40, 'https://1.bp.blogspot.com/6wLs1lQweQM6cjEx_RPHT2aXnd_sCL-mReB6Sq0rEjAx1LLxqEvcqz6jzXa_kl5PAp7Xkf1utVjisTZ1Z07cQYxddRQxiNbPqcFR63k4AwgKTUNZ1m35VErjTKb1DCwOXcMMA1y8hg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1226, 40, 'https://1.bp.blogspot.com/pLv53FmC2I2-QfB280ZGj8nDHka6Zn-ofRZ-6Glj5gIWw4-4qBNWH3mS3nU3NXKBiXtJRZHXR3lxLn2P_dfdogq_fEg9HvkvK1OiUYvt7uiZbuSuPXKpgK-A0cecqVuoCSIqE9Wz-w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1227, 40, 'https://1.bp.blogspot.com/Ksum9G0jUzlf4VElwcL_hqYxOgzsSOgBrdZF0oj9ANoOfffEmBdesWmcs_-pj9vqUZwpmj_fT1RY6BrYDqdzjOcjZM6XaMhusn5UVJ6Z73DdgDhoK6vme_OfELoSmCMgYXx8mI248g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1228, 40, 'https://1.bp.blogspot.com/jQlDcPaqcY8ZoBBAkVsRNIXZu65WAc_8KCMklPO1nvwQM0bHvn7SK5mMmC7tBeCkKFpdAQWhO4JodvRR47i-bafIOjPMJfZXjh7sJit6tTQWx27wryprd1xuGNuiiLHJJHXjlLw5wA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1229, 40, 'https://1.bp.blogspot.com/JKNxP28d9ySFaXIdXOvcyVJermb864okGnpy3iSJf2t3zMhp7SmRKqKFfW0gW4U9vSlTKrTSl7niw-KXKNY0pwD_DzgDDjOqR_jLhV2rxvEifhtVODDKOfT5O4VkjaCtoJnKJLY0ig=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1230, 40, 'https://1.bp.blogspot.com/-lOKNQLSWvKmID-h84XEmk7Xx_v_SgyPXypP59_Ts0qz1G_4GJJwq7k0UmBAvsDAZH0EO9z9AyLDkH_i31N1Bfps7vaxXYwdLxZTb1Ko0e0N_1L1ZY8YcMhX6eZ-sIHv5mymqIhZIA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1231, 40, 'https://1.bp.blogspot.com/vGUS18yJrCFvyaATI69Mh_CrTkv_v80Du9vHgoBwcL8DeAUGjYqitR0-_zGtYEJpHMkBQCwLyg9u5Y0c0dzPV-_UjR70IoXPDMAW1n3aVZDn8fTVY5sYw0Tqhg4NZOq0izjjymlnhA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1232, 40, 'https://1.bp.blogspot.com/pBi9fKcRgWsaTnqYocorG0AW17aeY8fxkbkBLCumbt8W9FYBpAXwa0Ugik6vkaYVrFMCOa86iLw238m7AzWD2JR4o5DVq2Og06NTsBcIR_qTbwShT_HI1wBg2kSQSmxdMsSBjXLwZA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1233, 40, 'https://1.bp.blogspot.com/__sRKohf406ntfCR7tnYX1YO_3AUD278Bherx8qrLvmOYGpM8yT1e2_SsOD8Lb_ff3LPmv4sAtXbNOcvOMwYx4r1Mt6cgGj3DJtMHL-yLmkvIdMj49m0p_Q1afqAihl1yu0rCZXOIA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1234, 40, 'https://1.bp.blogspot.com/UgZJQ9eUP5MPPAUcRln0nnQjN8sBQqYLdo4T9ra3wjG5pvnsFjb6JKCqZvSMNyLg_OR8buHxrQffN8SE8NS-rqY3MlJKBtwdLbeDrnpBc3i68ZPMWjf1rXMxF03SN7H-N6UfnrZgXg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1235, 40, 'https://1.bp.blogspot.com/qzb5cpYq5oAeuzHIUXwFBO4T2nltMiQ84XrtwlDx0xMUlEPBT3JDmWH9h9zkXdK7kQ8xeHjGf_MSmLe6VEVJvMAruiUM-yXgHHtEGJbI6ubp0jLqKwHZbBUpRPbDrkYQoMoSKGsNbw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1236, 40, 'https://1.bp.blogspot.com/1Hpuf0A8KhJBNntwEGymwWbqPylAfCF4fliSfzHHSnyoPAHiHfKJz1aKHm51QOMMbOOizvied4WfiWTq5kREc5xUo3AwUHJ1_zIGS1IW7hH-5VrLLM2MGWAiP1k3diTQ-mlyNgUZ1g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1237, 40, 'https://1.bp.blogspot.com/lm763Vxz90FDf-tTUmRLafwi8awBz3nMoIQKAyf4EoBnJKVxwS0IByFXWzYVZoE9NIZmvL8VpOoFPqyQlu4wrIpTrE-Sk5jMSfNjLPVKkAGaQsztExNeW5zgESbFqGGZS5WXIorl7w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1238, 40, 'https://1.bp.blogspot.com/h8Ef1JzZxb7Td5BMM09sMnZrbL1FcHn2pwVxpxkAeuxQ63scLdRcsc3jfIz4-HSI7Cr9nW5pc_EZKZgg8pgOpIbJrI_biej3QBcsjwTCa2EkXheohSXOUqXahsfWQglXBUbMcKdtrg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1239, 40, 'https://1.bp.blogspot.com/baY4t_a-HomrAHxFEz7k6lOBn0LmGBNURUAlKPNbmTihcuwa8ZmcUL1N-HWVX38n7U_FHr1lR94QlSRbuv5Au_qTqOsRqzPLEdqmzRBlokmYMflDxNjhOC1KSrzLPU1alodHpVFn_w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1240, 40, 'https://1.bp.blogspot.com/a3trxXnn2wH8UE98TnirrKDe4_HrOOAaFCh5-bsaYfAN691JLY2epmgyg39A7ZuYRFHVghYzA2G72-4veFR3EtFdgawytz2n4hXA7QYk_GWd7rN7phS-u03s1rII1PFHjDQ8OWlaQQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1241, 40, 'https://1.bp.blogspot.com/dTTG0j03f90_KklZ2xVscr-aevBhFaOe-PG2X4GYAOqOREsXt_I8B5W6bWz_mjU5TnMhXHFxBJjpvPG4AbifuQ0kdeYsVdnMJG5qBuC2YxIpoaEANieRBh_O_FwdiCrd_aLkrwYshA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1242, 40, 'https://1.bp.blogspot.com/_rMeXmys1byIMLL3TDZnChcBqKJRU1tHnR0f9C1LliKEcSKrCXQ8WGakWcE-SOmu_hllHqdrsbkm3HFj2Sqt2uq9w6x0ffbv4fW659orJc6eoAGi3K-L6iGqkakxhQQq3dB0SeKgBQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1243, 40, 'https://1.bp.blogspot.com/NR-8D6dWs3LrZx7Pe4fh2Mjrk6Khkti49ln6nnfGWSU8eVyIh3FFXqfQ-CQ_xfGvjHm0HASOxgE4wSWiV4h5KrfeamawS7sSisO9QRam-qYStTxJoQndFwY_FjZC5oSLDGrGerQdXQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1244, 40, 'https://1.bp.blogspot.com/JazOoUNO2ZsXZsXUoyrovLRft7DAtpuFFICCem0jUyl9jeSbVVGuC3o-qbITOtPssY8gHKZM3WiCnhT8hp216uQ0x9WItJ0pikHV6ENHNMyb3yV60gL8Z7mVmOgVgA-mVrukTjlFTg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1245, 40, 'https://1.bp.blogspot.com/vVVapRnp39sd_CeMGLd3-547qhbAkVVA5QMb8umuFR44beWeLhEhBWVtaSycVJYx90lGtO1IQj0soq3KlLb8BZMuT9SVk0CvLLpje_-3bQuWr_AZzpO3YyUDzJ_qgjQlmobbAT56dA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1246, 40, 'https://1.bp.blogspot.com/08-QcRTwgWdZzJ7XmcaFraImrh3Gj06aLxF-EQjR1xPr44PY-cHM6dIVSB1H0n7oxRgX9TByyzvhC8AHlaMDvzZLxAwW79ozlYPeJJ21-CNC5WphUYCyuEoPR3O4GL0dzPiSReIJlA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1247, 40, 'https://1.bp.blogspot.com/UD6HhLog7N5_nC8pNlxv5hBcqG93iUYqc0VzI-OHZ9B3EmsYiJJpSWlmoXSy7R8qoWuZtjHYgeHUP5IsxEQkJX_WgbtnVE2gUvtawxBbj2nDZ5jiejVAkzmtx5s0vT-bYfwZXlPqeA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1248, 40, 'https://1.bp.blogspot.com/pvoktwMsy2-YMPHKS1HPqRDw-7jYlw13PFvIhhQPJtMk5y3WA3kcd030y5jTlbJPESbiBr46HIhKnyOT8op3CxyhiSMY8PN7UPkmIVntaJzWELRioBi_jzNTi3XpuQ4KKOdHrqvR5g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1249, 40, 'https://1.bp.blogspot.com/o6cJNx8wL0YsDO8CGiwzGEJfBwKIgk4S1fXBbTFlIaSrwUCsWphIdpxPd2EmsCyoco5tgJlgUlE6SFKJD0Xk36tOYe5tI6lYkszCdpOAARfAapksdOrFSlPVllqZhe4Bef-AFjKSWA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1250, 40, 'https://1.bp.blogspot.com/DJnG2NXt8yFUG8wbGkjqlKPuS1zvOhATzs9kGEpPX6KPK6mMnAQYyLfGGNpoN9tncsxDb9I3osQIovJN6B-Q7rILDvdA5676UL4-i2c4z4n3h393Qa5-kljbF03KUM8av4gmFsH9gQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1251, 40, 'https://1.bp.blogspot.com/XLkwxeQk79QFEVDMh06KjJTJXHtGQ7xx7xXMkyfZDO-vG7-OB3RYV5mWO87UYeejnmsMwKKLlRjClOyqV5fP19mar2FsZJQZ1O4d8lhGAu1cL573utVmJHhui5h4C04A4K74QSKntg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1252, 40, 'https://1.bp.blogspot.com/6XVBLBo4ZGL_D2Vm57H28nA5qYDOkWNeBgQs-pR83D-Y0LSWQqOAMJ2u2sNy6NK1gd3JnrYWSymbwx5PawtnoYU4A5SEXK2J7KfYG5Cq9qUq16LDWdQ_O5iWTaM77de4IItWKb5bXQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1253, 40, 'https://1.bp.blogspot.com/kD7jiUfTGjXYtn67-rFTnTffK_sR-VD2t6f7UFpPNOUV69w-wWFD26tLx8OBpyVMUxhJLeqQ-jvUqhpdJNceC-vnn-BinHIFPX0rAULwd4ywd1S9zkDFQqSps4OufuxjBpDlyZFHxA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1254, 40, 'https://1.bp.blogspot.com/UqBCD_Ebe0yP7c-A4zxBYMY_u0b0J_k6rSjan52aD0UpM_6oge76ou-Viwo3H9TdAemk4icZJPsYtZLSE40reEJbFpPYI3WV4-Vskv6cZCqBmDCbPJQnO4-_lp24N4BmKeAozBzN9Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1255, 40, 'https://1.bp.blogspot.com/PSfLZNXHxEcwXkQ_mOT76oq_zolXHPThFAfrkO90jPXFFJhVF8aBbY7lrhotDdqZdBw6WUxFYLNK8-vOBgJD4ehy9S9F83P53LU9q5fpy3Cv_BjMei49t6he_CstFegzx5eRpWGJ7A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1256, 40, 'https://1.bp.blogspot.com/D32Ya9I3n_Y8t_K4nbmjMQFOv-cTO4CWOetXfvi62K-UtpIqC1rUnMEWFgln6-1Xd1OFPITN3yTRTVbG-ZsYoqj-e9rDza4BDC8OrTfWZGx99VPfNV_PnPw_LP8ApaXz-IsawO0t0w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1257, 40, 'https://1.bp.blogspot.com/eI-NqAZ0QWq9sww619XgKjFci7AOihcsWViGUeGhP3D8_ciCio8RGEYhUi2wpUDCZzNSGD8ZdTCDWqj-ORauJVa_J_mjlZSciJpwhFBmmbylI9J9jlztiSWkxx_sUYiktnpGK_kVAA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1258, 40, 'https://1.bp.blogspot.com/TAHJbTWvx3oLr8SGD3y-Pawv5MkaZgq--zptjjoqjeJK2PKNeVHrjzcmfA0VJ9CGNq8nJSbQrxdC9dQS-h23lNVD5x5aXoi_1Pe1ZDcqzyRhQEwf9rp3r-xFQGbLuxfSDQvu1K02uQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1259, 40, 'https://1.bp.blogspot.com/s-weogDVLX9Msrus2YSgB4LG5YyE0Vo3RzrZlvOyUM092sHkcQA3WPlQxjuACGSghAgEzwUhcc9Ysp8uYY27qCVqcq9hg7A1ZU0Vi3ImDE-4SWeydOF6tYzkrdFmkWxYORkOBaloVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1260, 40, 'https://1.bp.blogspot.com/HN2h7hea1kvXMUU0ybcabZzs3KuPVDEY7tzZSf_eHPxX0byA7Re_OPKv-Gq0sJbjY8VTO4GIIp4S3ppeBvlQroYOkhAYEs2VzSzGO0PaLGMhIAqsKF3YVmkLbbqbAWiY8OpwTuU76g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1261, 40, 'https://1.bp.blogspot.com/3lEA4jhqvu-QeN3AqRHDtwC4a1abvy6N1WIBoaFiORdQ8zPzeZmlxz81cQSJQ3yl-wCCsXm5ZcSdHnXSKD3o2K3hcypOPOSB4ajC1gq-W3yF-Lt_gJZcWDBrRw5u3QEOhCcjYOBL7g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1262, 40, 'https://1.bp.blogspot.com/OkhZxTIMv9xCuS8YaJ6KSIAuevGiEUeKhSxweZAXi6NjQ3SuB6zeYQRDyNytDZRHl8a4krHPbg5SIHoI3aVI8Awi9o_wD4r46o36FoiZLWOsCPSTwMvkvwK7v18lNinJAlnGxnywlw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1263, 40, 'https://1.bp.blogspot.com/PqIpagvf4OPrynncfza_bJOOaG7H1n861QMC5ZUIRx8mfMH_kukxpfHi3f_-EbfTH_FFIXJutod3BwoOAFoV_aoWsh9p2G5fXbAob3T7Dk7XOpXNgSUp7FJ1CZwV5WZlzfj4Z3_XCw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1264, 40, 'https://1.bp.blogspot.com/MnH4NNRl1rxfh7EhMGItDOQqhUSdQBBPIdzoBY0y85bj0o332VLM7ycnf2PcQoc1g-hKUp6uap4iJrMyZglEt4Cb23dCVkRoBtZneJ0a7mbPgG9qd_o2Ed08ee1y8Vg12JSVzU9EFw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1265, 40, 'https://1.bp.blogspot.com/5A_ViFzYTmqq5gj1kGNvkrerG-ZcMP8cfD3bWPGdt5RgLA1bN8afiBZr5fPBWOKNzoIH0mojbAOib_PDerFNswXN2HdWARlGUMLLuhgHZlkjJga6oKyyYm60L7MkK4d8uwW52iWh5g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1266, 40, 'https://1.bp.blogspot.com/Ssl_QLPUKxewaQnFWaNj4NTFWMMpM-brV5KqGBSdrfCWDEpisLFLtwzL5OCcLv4g1sU-i2dDS6iAAXhJtEvNi3W7lp3sPgDjdvGVodFpN8IQfb5eK1YknnDqPpaiofSZJbuyTHwKow=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1267, 40, 'https://1.bp.blogspot.com/aLcbcuDtS7hH16_mrmQAEgqgwbQLy-OxyNghZ7VOALGfCMksbpoN4l6JtlgfS8VmEcsAmCp8uiPhnlFHaf-79PWAhzkkoSvIPbhR539aot0Diwdx1v1ShmHDbCVsZEmOQRhcS-BOjQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1268, 40, 'https://1.bp.blogspot.com/EqdpIB3DFyEGDCGCCs5QzMu-guM1aRmK6r1FbfHy5ZsQKavKKofIvAB1SstvyWQ9xnorIY6CVXy3PnqHMXsc-2EahcPOs8PZY3smd4ghNMBHuW8U6jsnSe_mFOeL2eVO_qvxLxr3bw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1269, 40, 'https://1.bp.blogspot.com/aJ80KlbH0PWFZ16cdh-HaHhhMrZb-PKtnzha4u5gUfWY-afnk9mtI-oR9_YYoGv-SpVagpvi65zrIAoLrfWsguccBTaPhObEnuBt1Wt7VAam2rZ8aHxi3cI5oAuNq8pf_Q7R3YejDQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1270, 40, 'https://1.bp.blogspot.com/RAqpjPycqYN8QmxMAxh8_VocoDXKY-xdxPMWRMDcgr8WHkRDWxpEPGX5KN0AfTUW-pu667iDjQIw5ch7L6Gcrxo23JkyEMRRIdIgjpHDIyt9leyCoMJsRU7Y_RgBn9OT69T4e58RuQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1271, 40, 'https://1.bp.blogspot.com/H9UuUOMnni43iauP9OHp23un2KOTe4eJ_WZz6eim-shfTvfjwiQuXkTBFFG4B7V8exDbbleGRiOtmEpaDjZUcnyR6mhkuL-TDUYEIYArUxj1gpBZ7wiQSka_0ZFsXr4y0swfDorRkw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1272, 40, 'https://1.bp.blogspot.com/fSoAhdbrsMZEreqRkwJBDV5fvRVL_HIMRgrPG6DKjSuNGkQT4LdAWmCy3E8asyM97I9j72f9ii1p0ZvQqI-i4Kt5ug1uP3p10iugUMUEEezQgc76TG5BX0sNHUHFZyQT8DCfNAzxOg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1273, 40, 'https://1.bp.blogspot.com/BGENcZid1ObRAx9a1lDCjtcVGWeHHqkLCE6U8bAdLlp55Q4hap73H0FoYgyLvBYXTlHw9UPycnmJMs4ogmlF_ljzv7p0vVEdZUmN-p7VTCeonhFheRppN91Da-PbIy7GA7A6G8dffw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1274, 40, 'https://1.bp.blogspot.com/XTPzwkRqbrJYh5xKSAkKGqeSyHl7TqJacszqG3PUZiwktxv9phm9GUoo8J949Kufimq-RLZKsgcRoVrmgS0u3EyyxEp2bxM4TJ-qeYCGwgA3_gqNau6F0NwFO6mf_wYm4v_-5zRFUA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1275, 40, 'https://1.bp.blogspot.com/FURxnQzmiEmTvW0WtAViJ0McJIbP_k0T1lrDotgOWvBGMdg20zrkzRRcUrTF5k862FSKq4ZtR1OANBe1WP0nWMp-oDDB-WlecVTA1PVdEVlgKBk8yijKBGAXe-C16TUyDA_A_ERF5g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1276, 40, 'https://1.bp.blogspot.com/e8ypa7qzfAXm36Lj8HFksGkaBgcfmnEsK315zuH1iN9vKUbaA4_udAiy5-K559jrlD57AIOd45DT3kiwjFbLimpaqqRR59wTW8UQCJB3rU02G6cGH5grFDPWkjFoRn2GaYe-q6rapw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1277, 40, 'https://1.bp.blogspot.com/PiKqOOfIoBh4tVg46bXgnrtwU27VPe0fxkb9Th-s7cC1bx5TjabTFADxPDI_58sOB8VOegifrBJATl7VojaFVjLYt11DbuNsSz-tYr1MTl4XJR5ZMmuTK0NiLGbD4lC-8gj_z5agSg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1278, 40, 'https://1.bp.blogspot.com/Uv6WIv3iXXO8JcyMDQsMI-77An9hi79pqm6yyPHp_PyP7e1lP4mLS5mFkHvnQyrjyNddQR-akesHQH2u0LOAcwRBPom7gfl_wfmNMwLLNlvtKW0c3fDanb9BOzoQgx7ytyYzOScWOA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1279, 40, 'https://1.bp.blogspot.com/g4BdHYzNjN2Rfewe-sVG_La-SiTYfSgg21SEqMr1Gahfw5MV5LqWCuueLPsNEYfCAoyOuCI6xcrUBBVEVJFUT3h8E5fljzPhPsoZNBGoJV6Xs9HxvSj64rHlpBvSEWmrhhCdxalB6g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1280, 40, 'https://1.bp.blogspot.com/YPvWO5XKy5uNAWamv38qSLegkvhUaoolsKYUsGzmrGQfingvcrBc6XN8RCeWBaG0yrdfK9KFIRI3XhY0rImvMl7IewQNcXC8cDTV17Xm55mdVIqVu8I_0_i0MD6Liy5FA-v-W8FRHg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1281, 40, 'https://1.bp.blogspot.com/CyH6lX5DJlpqEs0KHvjZljrkQjHWxcZsBluqDLi1sbpOrxWI_Al8-9FHkKA0x_QwGOR1JGryeeMOK8sGZGKE-3JmSRGvpTZGlZ9QYwUr2TwqDe-84vFRzv-DkU6iY-mcmsjf3s-Mvg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1282, 40, 'https://1.bp.blogspot.com/9BZlbO3sv_lACuYs8SyJKGVdlCrfBSYUjDytr6aTXrmFjyFs73tpDBlssjrPsix-0yGT_mt4fcsOU2zTD-jCkww1RAcTGMxvQ5i3vJMrn9X3bTtwJ2ov4R_2DYRf5A-pC4RZeR4UZw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1283, 40, 'https://1.bp.blogspot.com/bbQ7EBw9UZ1Y3PqSqfOE8ah5OLrFKkMXN1lJlUu7LLzZq8dQg1yHplNbflpxFxYvwADkM8ixVu-YGaFvXvK6KNen62iekr9sIksZOcX3zvSxOqrvuuDGql5Nu51HKHHatADmW5MuWw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1284, 40, 'https://1.bp.blogspot.com/4nhvmC6eH4EGB88R6iT42aGep7vFbioLtPwNaPc-tgw8pK3QD0oSyXagPjYgKOk4Vfe5Hw8k7dR6AupCysU32DsnKsksoFRmEVI7ffU9soBYW1AWsycgwzx0ZmxBdCf70iBOyk1dag=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1285, 40, 'https://1.bp.blogspot.com/Gxq6S7QLN523b8ZNxmDBYXiy94oS79F2ukE_gBsWZlUzxhEGZhIZLJdAdERbbHQP-9RPcMl3pJe_-hZ4uRY8x5zyCTlHgrKncLFJSx6RW3-X1lZ81NzGneEzeOaIfldCpTbQIcMspQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1286, 40, 'https://1.bp.blogspot.com/Ekhs4PNFxQo7SK9kxr9-l09w-F3S0eNXPDmQt3fjFd6JCDgBsa-hQmo9vS4RCghZW84UpQNmiheTcIU4CaSvLS1rRf8aTl65PVnQvk9YND_kw-q8udBJrh07WrTdvkAlpiuxzqTOBg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1287, 40, 'https://1.bp.blogspot.com/lSXcV4B5U1J9p5vhOsnqkhXeawbEgorlubOV4qdL0vcRGx55HgmNUBTZUNu4NqiNZ-VRbZ6encqIryfdxV2yVt3ZL47T37_X1vNfcAfcY3CxwuD0IyPOmxJbbnzv9-ka-OTvpk90Og=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1288, 40, 'https://1.bp.blogspot.com/yuOPh3Od-L98nDv1zFjXyh3lOkShEVdLk6sBxJgFHzBZjYY-xNNjWBy3qdqmKmfpdXunDgPy5cCKHfOdMRgZqsqWvpntzCB6S71pEc8CjCBKMPFv53RCQXihIPUfWw42-HWrLoWsUQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1289, 40, 'https://1.bp.blogspot.com/5KoArYSYoH_r1akYq3N2x9rVKSlXy9wcg8V5UCJoxbftPyWjD3RKCfXTWeUtDpNpYvq9IGdZwEmemlRM148Wog_tfMB3C3uOuHhD30O2sPWbRzEpT-WlUP1SN9_H97ps3ZlYzLxuvg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1290, 40, 'https://1.bp.blogspot.com/jBOCx6Wze39Epx0lPWY7mBgrb_AgZD2KLyFnwN7OHA9RL90RXUc1Ol0jEbFSe83cusnA1D1CpC101_lYH7IXoeDrwFgAV3J1XwSuxdRw0LIkP4UAZ2T147tcCe3VCE0Yr8eBPeYa1w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1291, 40, 'https://1.bp.blogspot.com/2V_FmZOBJuOs_5UjnHtpJjCtPYTEN2xbO_5yEW7Pco0lpzFfYtKzMP0aDU4Ls-w1Uph3uHKz1T7izdaFm4O3NM4WQ015BHhKy4Q4CrJmDxV3yjqRMTCCyP9fRRv1zjSNim3_4dkDaw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1292, 40, 'https://1.bp.blogspot.com/tOY2eSzupnZLPIClgDG3wi0OxcN0y0IMGLD-1QZvjq0iBsBkD1yVz3OS_uB-VeaWepkEK6GwqsoO-1kFBKDcnrZggFIKWYoOjsXr-5wiDeelF7CLWnFTmy6rjw7kp0RXvhnzn5b2Hg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1293, 40, 'https://1.bp.blogspot.com/YxXhjDN35_69PvKERC3M1H2PUzya_dVWGq9UNkfYh2HQxM0RUc1t09Q2wOUokyosDVNi3XsSaiIxPwrwSihjbjw2969UD1ifJmXkkBR8ZbPsvLXqrDGqTx4eeEvjYMEU7K-S_oBjzA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1294, 40, 'https://1.bp.blogspot.com/tmeh_ez16t4dvQjRGu9PyYR_DczWRhwO9XKmN8DZP8Sx-mElW6mE7oRzNn2nLBOoXSqYmYGAGENCi-5P79YHXoIlIRv3FmUOEkoXeilOrYevvl3gvzJKyorfkJLxkcyEpu9YJDokVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1295, 40, 'https://1.bp.blogspot.com/_2kJwiZ6kfpbDtDNLHdo9QP6LESwD3ItLyQUZiBx2v8bkI6OtC0dhFFXIf2aJ2wzruCYzY_5wPfPZz5Z87I5HTJkh42rjFoW97x9OvJfHpYgYbgwCyeNiUnOBlTqUKwtaSjmdjji1g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1296, 40, 'https://1.bp.blogspot.com/ty98ImItl6JuGs58cOTN4Thk-CGckefudJfLgNM5gUraC19w2ao5Utub-jl3RBuEH6t3xdhtgnMmWcbT3sppmumkQwYGQXj9sA8fcWLOMyZG2llMrzg-oPFNq3JfbXxe30IXGWwbWA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1297, 40, 'https://1.bp.blogspot.com/YXHWKLUz5UNyyRUfqDaBzBr7gokvzGAg1h0uRUIaloqTK-lMEHHvAKMDy1XZEyTv7GdUanqj14I5rFAAFqImmNGFi-XJee829hqJKPcwInvzkTKMZEadLD6sqs4_sHDqbgTRwas9Sg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1298, 40, 'https://1.bp.blogspot.com/f3I9iFcloHiYGgJbiOTJ-LDXIGowSdgXIBbzRyj8qCXPVOPTDlZDWBZDKUeOVMd7o7Q9fAnwgkYM56nLMdAB4uPyWmNWF-QDkXmqfsmEBKVNZdM4Vo3bEnVpJA6Ct6Ww820QHehE5w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1299, 40, 'https://1.bp.blogspot.com/qDUProLVPeqEnWR3qJDzVqNjko_NSxkDzbIOn2UcCFFyv0A6gvvdS1JNqjoenGXerNsLo6PDj-NaLVFQMmcf7fFAyQsxZGFt5xE85mV90lK_IRvAqb9psUC4EGvOC3zIj-kXXf4PyQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1300, 40, 'https://1.bp.blogspot.com/FJ2O0Xqdq7vv_yu82IapRj5ekSyZTso7RCDFCqbbiWyA6AScUZoikJzQ5GzlroiAcpKFQ1PFrTa_dTE_VArO47bTR94QAb-08BkuYpEippdqrnmu0FtUS_IZSUdM57YFa_mFoXwCmQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1301, 40, 'https://1.bp.blogspot.com/PAxDwcI4hrCk0-n3XxCJqeQScR2QW4SLgJSJmOitW4RJKwKn3PzhstNSTfYgbOaUNToZzvRiTeK5QWojUVrU099AjhrTAkwx9t9a8x5kMDCsbVmkI-dujyYLywVpHAgWPkTsmCD23g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1302, 40, 'https://1.bp.blogspot.com/xENBhjQX5MNUQAZ3uyY22uevifmkCMt2rTvO0oNhaI4bJIvBjLbdGsIzotSGou8saS8aAJ1L4HbpkMNSVTmQberKfaefLj0dtOvpFWEnyCujdUb0p8yyzivRL4KobmH3VXperQI-Tw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1303, 40, 'https://1.bp.blogspot.com/lLyEhfDfKoS4wXt5W0ZJz0QtU7ZyGLE6jTf7neYCW0jZfYJYRfla-0Te1LibDgMJL0MnrieYzX81CWNpx1V5o9H6M81Q44b-z3oRPEMtVWmbPQwJLWIPaoQKLhDGYN-ZnvdnDV4ISA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1304, 40, 'https://1.bp.blogspot.com/4QHdXRVgTASAKPrlWuxw9eB2c1LxicxEGUdMpAnISX_xhf_iZQIGHYtzoUhmVO6JGleMGz5_7dxfzbh6sU3qnMqs6A8_EaHfAOVh56PV-QUhfBW0B9Y6nN_GPeyxOrUceIfaDXDDKw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1305, 40, 'https://1.bp.blogspot.com/ZPZyaJ8LI5F1IkTChjLYIhyVgeccpPpcrgiyfDKj13Cn6TcoIaZWaUTU1t6o810M0GfPO_lKlPDYftX1H6XMALg16ta_Nw3XVUt9KFefQGIhhLLIxTtMS56GutOZmzoCeh2_E2ytfA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1306, 40, 'https://1.bp.blogspot.com/PAjYgJtDnaJEX--ljTGIlL-Ir6ohn5D32qe40I9xmcXPBab8bp1VjEK5g4o2ieEIIaggeunbng8TWvVMoHuhZpilCqOoPArBe0gzI2-auwzbYzEeGo7NXIoBvf5IfU_TYM2toXNMWg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1307, 40, 'https://1.bp.blogspot.com/7IplIKaMgZEGSjTpsJWg7kwfEcGxnj5a2yo2QYzzHeErRuMeMuat2NfvqfkSvUAjq5p5YwukzhNZ5zf5FBmcNepcnggaXbhHB3oPvMEPNj0QjHos1pqISVFyoPxgb072bnogSoJmaQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1308, 40, 'https://1.bp.blogspot.com/Lk-z1UXFs10b5h0HQc8ZkOGULvPKrnHVoaloDgc5rNpu2gQuTBIeeDGKyjbv35QUyBKgkowbrpGoTalwgViBwGX93gDHIZZtB291fulLBTmEQJmK8j4doVVc0IrhxqWd6tPL_hA4iQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1309, 40, 'https://1.bp.blogspot.com/zzslk7A3VyaDGvhgVGryvkqIl1XucAbrO4rnzkMwLVK0_dQ4nGuV4fH3DZJm55obCDWFAS7xjXTG9rvww_PVUDI529bXqOB8ycXmzRuOW2hj5LLwhfsupn896DCU9S102EdC1g7Owg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1310, 40, 'https://1.bp.blogspot.com/MphuRtIy3z49YMUEUL33GBYr08GPHFI71w2DuvCBJoF5MgCrpneIxZIDGFr4DrpFAWrXQYU5hNdAM1Rz_4GEfrj5olowfS5GHBWYhdkDMc8PU_MB0aiLncI-hIFTiZn_ecplKtUmIw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1311, 41, 'https://1.bp.blogspot.com/4Au80YPx7NS5jUYVqk4p-p5_zyFmLhKi5Dva8eSNPRhT3AoZAw0XrQ09VInjnq-GX-C3mlEKtaqT2Ab26spBrZvdt2XwKg0LFliYbg0-cOYGG-tbn108B5z4hUL5nz22NIWZ0k-jXw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1312, 41, 'https://1.bp.blogspot.com/iciTZ6HFvbzgE2N0Uuy0ajo9XCBviSkqYMOB7oJbzNBwzNeQxWirDat9jodPBmQVpealDKTVPI7EP0ILnlSH9RV-U9_Y_iaaREZaVUrugrC7tVadLbHozIfMKCJ5tFc2_7iOn-NGDw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1313, 41, 'https://1.bp.blogspot.com/VD8_uxE-6n8RTUkWLWjH_spW5LAf3AXkA9DQ8k9pCK48-1UzvVWn58s9_BiDc1U-sNDIaRVAH7Fplq6CpXOPfxP4gNN_ilj3bbVNH-0378AM7MQZ94zLfPPMAUEfUPvd2whlFfRB_g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1314, 41, 'https://1.bp.blogspot.com/kgyOiSqAC2QBzsDeg21UW-Ykp6rvT-XQcoQJfSaoZ9vGnZC9xkDloUXB3EaexVNz85IHtZ7gJ76FBP4AioeMdPXI1Qt8apv6UlIAXYgf7QT_k5pgN5EUKb4ODKNsuRL5rLUbePuOvA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1315, 41, 'https://1.bp.blogspot.com/TDvV70U14SHAjj9w_sC7JZPU9Myu7Kl9rPEbbbXgLSYNjXcc6i7nRTH_i3RX3e-p9AiYtDDE-1v1BufdgIDbPBn5IfdW1KhPaW3w-pMhRgXblTsa_USKspg9h8ror_xXjiWzUR7Ykw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1316, 41, 'https://1.bp.blogspot.com/8OooqlwpwcCJBt9N78JJtZwc2_602Hql_j2k9DJb7jyXEBxHEokQ5IRKoQ3PS8qj2sioRNZ7xamBVkul5twpejkYhD9l5fFlGlANDoprrO-H_-FeNPRPmKN0ca5StxhPyJ9W_HGaPQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1317, 41, 'https://1.bp.blogspot.com/j8nv8qPBweMnmPr3ztbHC64IowGry8cbgWLOkODUblHDBUvgU14zhbQNY8rTraErvuFGBykCnnoKJSuNM9jVdOpsBfF_uas2lZzGq_B5oU6QI-WhI7f4lu055vUd9I2nLg19hsscFw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1318, 41, 'https://1.bp.blogspot.com/fWcWKA6Ri5cIWDq_olyaPUeUF-d3V4wTpK9_zSm0Xxb1ykmKAyPgqbFjeWYkpahStmzV5enMbf4nOGWilHMjPirT3fN_coRsKEwjBsAtqjqwPlnUqlPuLQJH2Bjn4oLbfCEiXmcLwg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1319, 41, 'https://1.bp.blogspot.com/sQaNvK392rVduc0wk1qxD_OT-aB0qZVMBVAACT5pUCGW5jH_9SrSWox3FKjDlq9bpVemKcu7W-ngUkqfny4zPvoRMLb3RTHFKN3-9kCM27UCb-xB6CZQN7EcfaFTwelUYc0l5WyIFQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1320, 41, 'https://1.bp.blogspot.com/elubh4PmtIept1UReB_aWwa4WgcQ8-Tpl8D20g1-7mfvx21um-z350BGL3HtuoCZN4qT3x7BuchlhfLUvaLfCrVwRrAxDex1w38M4ezGbIOTOH9bn1XGZI3KX1Ieyb-tDcpxDKSJWA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1321, 41, 'https://1.bp.blogspot.com/NEMRdS5OIOZAArT8tFwaMc8Q_-g0RqLdMJy0NuiQwolBzu540wXugokDRwmPUDHML1KXkia5sqchUyFq1MfVw0s07fgHxy60ydbqEG-ueeugXKtAZOqCODkzislKvuHck__JUPl8dQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1322, 41, 'https://1.bp.blogspot.com/R3_xNUDo1c42tVGHLEk35Utq4wQWSTgEadaTbvp6uxQSwYrCPk-olatVJspx2zIrBngeEVoH6QNpm_8lRi5iPHerf8yjnK_-LHHu4hqurrntC0mRVDxm-WG8cBLM0CdeCCpE9pLryw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1323, 41, 'https://1.bp.blogspot.com/QQvmULJKL--8SLz3gizaAhMnHaejRsPqLr8pJ5RpbbPptfWLGUnS7UxN2WPsK-DMPtWwrxaVopIWh06fkcfsuUTO68Dz3na9O2pP4LM9z2WFEn5kh7-AZJM2g1MNgSRUf3Bh810mpQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1324, 41, 'https://1.bp.blogspot.com/IpjMDJbmIlpBU6BcxVUb54GQHJFa9EKPdryGqQxAmsCrX-XPMJT0x1Og5MCWaC6MqZ5trW8vWSAvCZlHeIIStjLn6TArLP5OMesCPx-sdzaqaM8XMGEo1Fvw0hrjAbZ3zOIVO2sIFg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1325, 41, 'https://1.bp.blogspot.com/ZkCgJgI1mlcQUz_AyeCZSU9E_TlAyVtzJ5vvkSRegBnmx4XuP43YeUoXofLeGZ40lpmW9p2nnI9Sfqi8Fmo1CXnYUsCnQNqz_6z06KZCU7CBTfcreQbeCTIyVSzV1swujdT3ggtxQA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1326, 41, 'https://1.bp.blogspot.com/TqFJMTSPFDBQ3NlP4NJRDWJxXbIhrm1THj--snnavXJXf9VDZl6q7TxY2k5oT2HcIaMo43UnKmciBYGhdDHMK9odFssifUv1klGDIbrXuA4JJnc2UGZRi9HQDm-2THM6smDQNJrUsA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1327, 41, 'https://1.bp.blogspot.com/EaahoUXuzxU4R5ic9sjBLDxC1Uq2nUp_0Au4Emj8XBSSVEM4tl8MoBEhInslmDQpdW0p25NhEDiyRNTnIZXxQolOXAQAljZQQjK7o5gAE5wkBTnGO4drWdaZerdToLhFj4q8YkM2Zw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1328, 41, 'https://1.bp.blogspot.com/WIMtP-mI00XA9mIX7_6UnrhIwAVNldy6AUliTw5hNHBf41qfjkM2h0SiFUNWpE-Bjs0ZLFjeEXrWJqYyZmHZnNm9SY1fJsJqell1AkJJd7CovL8TyGydrRNIcvmPGgrp6xtwkIixqg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1329, 41, 'https://1.bp.blogspot.com/SatDugMUha7Kis7Stbh-nvni5lN3q-sPiXZpY4EwtUT3bg91I3cr-5FJaAESIvGpLqobFBGReqAm8BFEufKloJVRveoI5cRX0pUFtARkvbx-DYmoTj1j5vxhycXxGfOYynZ1mcI9KQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1330, 41, 'https://1.bp.blogspot.com/BGTQloIXLGChYqWhyM2_lRDd83v9QK0k0yihz_5z57lZUPiPBFJdGK6A12K-y2OYWYocal8G2S5P9yz--aqoCy_wstVmtaJGLNP9nsjYa4x4VaFR00-F30gTvCIqEY6dgIf-CSOPlA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1331, 41, 'https://1.bp.blogspot.com/seLFaS7nqVIKR1AjOuUamRTTJt9LZico85ige-XzpuXMlRi2uKEwpZT_WNzjspX1tXJnoxlK1EpcPUXt2va4LH-Jo8yBgoR18Ln57_7T-qe6UT2c6V06A3m76wFJ_a5FtaScCNMXhw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1332, 41, 'https://1.bp.blogspot.com/X5lJ-c2cPZDIU_r20QI2YI66a6A7uB1X1UZ0gTrdMT0p03c6S2baoC_O2fhAJoOw78MPZcG0NfvMIMg60jA2zvH9QG3otbGNVmM9644BKs4O5badXCCdRvsb-h9afh3xBu7ysFZ9qQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1333, 41, 'https://1.bp.blogspot.com/bsY80c_DkxANrG-oH2GUoKklqPO8lJYxelnsPSKJpCOmziOmqqjI9woPI_SbmmIpszWvgXU04BeyWp3PE_KZTCnw7MlhJtZeN6CF1FtAk7tO9sTECYsN6XIRN-mHWYUxA0nurUYcTg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1334, 41, 'https://1.bp.blogspot.com/4X6u1g0lUXtC_VmELaw5ohrBJgSd6ltMbxeBqojDtpwW_dIKFTvk7AtdYL5YwShsNesFDiRoOV-Q9X5TLdUv01vg-SAXPj-RMEKL5P0TLxeMt3AmiHE1fJDPKupMO836Mj5W4EaJdA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1335, 41, 'https://1.bp.blogspot.com/kPj8ZmS3z1-BZrHynchiVfFPbBZs56Y73s1SMPl8DSCZc6dmCYTb_3h6DGLFo9QfozlkDSGe-utNDOGSti-IzbdMczxGjbhK3xeOniKmQXLu6hdhpb-mpRA6gl1EDwjqLt1QH901BA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1336, 41, 'https://1.bp.blogspot.com/9faWDN-g1G7pAr8aobA67fjZmwgMWVYEHJgjZ1nZoWDPvhryjlHPytzgtCPJN7Qq4YAWsnMH5IHXtc-UukAwZKGx9edpbrgjUJ29kojWwdz1VN-ie_TgN0e1lsdJXc5zEWt2qLE1jA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1337, 41, 'https://1.bp.blogspot.com/WhDdFvM3ICDMdsiqJUYWCfoD9MecqNz6Tfr5g6AyFPtttXC2Q5zjv0swLwUqR_17sxs2oPaTerHym7oOLrLHfudnAvKv1p0BoPMkNwnmEvw-f8OhHtSbfJYPfYDq2GYyuIkY5niBmw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1338, 41, 'https://1.bp.blogspot.com/iyuntvXidcrLMKe0uRWUgSc3X6X9GK8jkuEdK2q_iVxjZLaAacxZLk8W8gMMaDdWXOPRgwXa_65kjesZqTtzI9tKZckbfdsR5ySAMSxooynh0Vyp-q9nRmYLic062rUj5h5Cn2CJVA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1339, 41, 'https://1.bp.blogspot.com/NnfiYlUhsmPI29H4_vD0NrE99XOhXZov_OAVhfu5CjouCgk3_4H2MWn12N_cijKeRtbtsmWWHOPa7DnZT97zv-lIxsd7du10Jcq6TKspXyvIeAiNMNEicvYgjSw6B05wva0oqilbjw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1340, 41, 'https://1.bp.blogspot.com/Dto_dIqilnlRMo6Faj39dPeA3vPEAqAZZQtREBxafJF3B2WNnHakOFUE7IROtvpMbstFEio4hvfjNb8rxEdHTg4fXgZq4cz6VfGBMKHK7pDiu9tZ85zwSliX1LwtDPW6i8_jHB66xQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1341, 41, 'https://1.bp.blogspot.com/9wm66fmg6ODZtL_279SAYRaZ1VDbZbSqbAQjFEC2_4FY3mq_YfdnrJoK_ZBnOe9BtjBhqeofJ60XqcMphLN5IlI-VTvWerS0il7YkLc0IORgymOeZ_kCNNC5ImzWcz38w_ow0mBvvg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1342, 41, 'https://1.bp.blogspot.com/yNTjrNkzVn2B5k8xce4WNbYGohl_1FX0o5rzYi_qGiJFdsWKA1jtYt6P9dvhfikGLzbmYPc1QqNr7yh6p-ZnILfIkYO7eMfQws223P6nwTIMcl8kbAgXV2ydRJzB_kQ3OaxQctCVOA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1343, 41, 'https://1.bp.blogspot.com/s03bNvrxpm0rt83clht9ZNOPL7tiS6i-zRS-6zAzXycDlp4YZQy-YpSvv9vTGvC2gJoQ-X045eRsoLorcZnavCFH5-YsKXT7AgKW2cKdIYprYGiJR3HlzAVrwdiCZRSHfi45zzNODA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1344, 41, 'https://1.bp.blogspot.com/vKbTkD9DI4o3c-BW3b6irpb5YGvkgUU6M-22fwnk0asAHEysTdl4HPJkGDYCt6AzEbwQkerW7riXvTn8AuofkdpFVpBq9c3iVw7JYciKDPc9R4usGkKlFKOlBDAhHe4ObGfmGIeP2w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1345, 41, 'https://1.bp.blogspot.com/tnaroJVSPAq2FPknOPOWCqniEVV4Rb7ycf2Hsc05gcO2ruwIx5vDuZsDeiOA4Rjbh6aMhCUYe4DYyTWAEWP9tbyHbLKQ7JG87orb99m8AQ_5WrUTDE-JxZa7Zvc0N-2MvmoWvS2L3A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1346, 41, 'https://1.bp.blogspot.com/nF0H-Lgs_2LUlTbFHzWCsH1vsyF7KKe08NKDy_p-gHIOZ2X5786jGHI7I6sT-kiKHBnA3fvgaBAnhxKc2E1AiArNluE4YGqILB_YVLnj-sXwJUnJyAqU49vRuXg0UQ8wsaiE8eOx5g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1347, 41, 'https://1.bp.blogspot.com/881yLUM-Ws0mM6bqOBVAcBcV1Z0FLR4XdqYIkMHFXL_leWp21bRxXKlybkw4t33yyoritIaZXfL327XnZ7QVELRS8gepBwVIQgqh6hetvE7UEqRmT31zaVG8s6bmxBHzSI5k8la3RQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1348, 41, 'https://1.bp.blogspot.com/uBsKCv-5nmKrF7ARRVSWPNFp6pRM8ZCvpKfawkTgjnVUTAqoJohtV0iGW_ltuZ6UhbAFFBYE47QbJ0WSel_8SmVx5koDb2S31V5P4la-PcfA6-N4lyl2aExkS08BBvIJ4PJkESRELA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1349, 41, 'https://1.bp.blogspot.com/WQfY30C-ahtgAQ09BKtOfV45MOhdTd5jXxeOsZ_a7v9T6Vs3shZPsa_j8tmLqVU9QrvrPseQRX0f4a87-6-urAASP_IiF02061uVu5DcwZW6TzSwAuS7ommjNe3cIjCoR_aLFwmlow=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1350, 41, 'https://1.bp.blogspot.com/KViphbpQm_yHG8Jo44007uIdVLB83K9mp7Aqu-BYeM7LAe6P-oIb64fmnp-mxydkc449DHlstXLz8OWHBDPch3plRVRTtGosyu-1CADG8dm0IoQNkMNjUR5QhDQMhbKeVaGBDPO-wA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1351, 41, 'https://1.bp.blogspot.com/2Va4uVnHhdq72JFYEH4svYDG6qQT67L85WSypE73TBUjsCZNvi-PKtNFweoLC5URJMbwpakCQbwV3YGZ6_OiJE2ynkWCCoEl3yJ5Ur_snwoCJsoS4Epg4Z8-zTdlMKHPWMZivPo4Sw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1352, 41, 'https://1.bp.blogspot.com/x8EWEXZaRPFMCoiLZI_U35mn426j-DPRbu36xcBwKkHR0Af4Vd_TZzzXniu8fJYbCLfzpRR6qBHsEAkGSDzxlOJ96-kPvFjHn-NqsoUio7GFjxYMWF1ZuGneSXZdjbKl1jCDqml0og=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1353, 41, 'https://1.bp.blogspot.com/7Oo4GdI99mjW3vI37VVMYg46TZXEMnosBu6NdfoeFyZA17XBFVxOQhA8CkbxJWhCb2bvCDql_QykKOB3qANEXVuTv3VqFQJmKkY-TGs8xF5LlAbrbFj68STlNy4EPaBHXa-BQaRCaw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1354, 41, 'https://1.bp.blogspot.com/SUE1SNGdDIXRINomzHsfBUNYZpA-PI4Y-O1WWQCaCsaRM5qxBQgcawcEDbfhD3sfsgmbsQnREVc4u7MQvW3e2zixrvxgTQ6xLV3YNOLbWRqX9hCogjMaQHfq8RNLUF4m1OEz76jDVg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1355, 41, 'https://1.bp.blogspot.com/ei_Mtno___Gr1OtFVm_ef8V_etQ-8z_PDTFyQis62ZsXaOF6sv_JxBNiUvDY8dxr94-f9emKVyhnJ5-DAVn8LKMd1EXIRWKz5Muxs18iudAYhu0UiyFAJCIjEXVddG0WZoNCvvdbdA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1356, 41, 'https://1.bp.blogspot.com/tynqOFTcPb4_DHTXBIZmlmjwUB_rx5OdLH-yXFFUNZCGGPWOyBYX2dU5JQeejI85ujAohmpcEIXhec9EJuwPj8wCVw9c6onS0RsD225D8YILDbJqRLm7BxzPeJ4rJOouqtwlyou3EA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1357, 41, 'https://1.bp.blogspot.com/vcyZVjixEKuT-ZFFlJSpUPr8SeVhxpsDlIaCxyVkXmOug52n9L6xQsWUk_-f90hIX0c_9Q2mXx3GYdFOttTdvGkgzLmmDVea3TrIO07AfEa-4ZX4owHPx0MsBtxkJP9CNlHJFLmmqQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1358, 41, 'https://1.bp.blogspot.com/mTmnXGyWZGkk3LpbF6k4VJB00ZkWwi07slVCzn32gi_w_mhNuP09a0ZtBrISsww9xLU71vs7-CFP3UOh60ywJ9j8DYEg9RkILb7ehtR-WT3BdLSyj6J762xaFoprATGwrdpxUbdkUg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1359, 41, 'https://1.bp.blogspot.com/67O4xAZpzM_asAzTe0XfxRQt80liBA4t-NpLxVk9CuPREVgmcdtpwPJaeAroxCbtwRaSRX40GSt71PwYrESz8Kxuew8G29Rw6ZHgEQLzgkLpjVWw9OUCxd8BNDf3hlliK1pO77XHaQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1360, 41, 'https://1.bp.blogspot.com/Hg7dUBUCRuDfZO1tIYaMFKon6X6pYrjAVfKb45oJc-5SmmoQaP2Vb3ykgNbhTmx1xu_w724QzkCvFpXxVXApU8xc6MGsjRgIbof79qMpy49EdnZQQzW8PUd5k1DZhwKm6BVpV1XGmw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1361, 41, 'https://1.bp.blogspot.com/Mbr9NV9wbvaPlHyPi7dws2OzQtjMY2a7QwMxGRcRfkwAavc2y5REfvLH6kOIWUy3auRzKVQ9udCiD39juU1OI0BdHZK6WWXqhKfPXz4tguyjZh61KDfh9MqQsbmFLAd4ofpT0K1JDg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1362, 41, 'https://1.bp.blogspot.com/8sUl2y6nbmq5W3Xol6-aGR6CdXj9aPZEsjRyVkWbzIoi7Y-hmg9vLrR7VBUJk9At0aKmpFqtKshdbicRF63kF8nWVxqGvTB6Yjkl-fPvzIqwqKtuIdI49eldrQtrT6pe13roXF7Ctg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1363, 41, 'https://1.bp.blogspot.com/C-j39IkS_Vmk-oJOWpiPG8tFEib0Z4KHLhAh5RSuvXsNlnju6PM7hsiRvYLbw0kLJVjt-iME83qZgtY_fcBVaAsq1vfwSWQf3rzbUFaElTU44Wiepv7TdiiLcGfMUDxeiKavXNec4g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1364, 41, 'https://1.bp.blogspot.com/PmiRKU1XZplG4qbxcJqmjPdjjbv3MYd2jTFWyoSIW_c8exK10KvZq_4VzirJycblVX50EA0cxSAtIdniyw6Q4m94LAykfbfCXriCqQjRrkTOLC5hMhZBGz1YWlztitPikMOJ_7d-4A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1365, 41, 'https://1.bp.blogspot.com/GMfZUnHi05B1Fsukyj43K4S5uAG69X7MZzvY0o9OIriZ4YF6P_HvARB8wY87h2LwbYbyXrvOjvvoNaU51_5sy-acGfqF_83OCcq0SbE7RJRHFKjAPZBAKgneGXm4VJkV18NDKneSyA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1366, 41, 'https://1.bp.blogspot.com/LQU8avfRdaXojOhb9eIXlDD_qc6AXuDklQ4ZEQ2Uil4Hmn4QOYYy11dtNXHqaqpk_ai_qMiuGSOJPor1SJM4vyan5qAWDc8UBMmcvV9unOhsTPfC43X0T4nFd4aLaixVmwdHQJSACg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1367, 41, 'https://1.bp.blogspot.com/ROzmF9hSdHaaqjqxve9TPh9_2KtbWQhM45eaoR4NLp_-ogASFqlnp0r-IIyvrVLuD4tIMYed4lIdnl4nXVbdP-IWs91lehesEagHRx7R1g67Xqo7LmryInw51G7SldHfOT6_a4Bn6g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1368, 41, 'https://1.bp.blogspot.com/BKpT8ZohmLR66MFy9KSfkmJLACjzNgXJweC8-NpA1mn9GC6yTPPaJKJHrLwtnLt4jpDGoQ_zPCHxGqmE2uXdpJWUGcZfjDd5Jbm1fOvMYmq5OBXVRmWfDh4t7KXNr46caRaGB1kPYQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1369, 41, 'https://1.bp.blogspot.com/7Dgenh8vmrmJJ2FN0zOwOdJ1pLL8ucbhg-TyI1BTgxLKxH9kL3IWNIEh2Q3YJK2TR0j1oTWm8FmIK3iroZhv6ANL8zUCvVRb5k3WRCBsOWbrKPZW1iueNJHLXRVXOKKXTNqOQu4T8g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1370, 41, 'https://1.bp.blogspot.com/qFp5o7SwNXiOPHwz-T2Ba-PskZ_fNo58ala6X6A2smNuMVSlooNL1xtgAA-Hvuf2CZaXnoHR2zoSFiu-NrNvk2dddxVexAQj3RAd3hSEgfFUYILo1HiJi_4QsIt2UNGRM7JLPFB_bg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1371, 41, 'https://1.bp.blogspot.com/xdrUSLaCSuR3LgbU1_dLZwUPgro2vwWeU056c9uqCQrCDNvKitYt7ybYgYEHcCv_-AzQd8vIQgAlobCbyDGs-sjJvF5hZrpgFa37_-Qc_9JwME8n1JAe4c1PCENtqU-IdSyI6GzMkQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1372, 41, 'https://1.bp.blogspot.com/i5s25HbkAPRiDUGJKdIVxtBgPG1MVlQo0gHDaLL56bLuvWbyth595gVF0osgpoMHe8bhJG7JohSgy-MD716d_hvMOsYa2fuIaQ-Cq25mqsLmQbZAkBhqJ5Uw-LpK9PqyR1f3se8Gdw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1373, 41, 'https://1.bp.blogspot.com/gWREhg_rKaMUoEMzaBpYR5CGGKVgQ8C36qB69eHrNIS8isbpiWHtZoZ5esW5v0raj_P2WEGtCFexDYw9Ffw-xddCVK070eQQjzzFVoA9dxDOu9LMZqV9KIQSqy5bwx4j9U6p3FHW8Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1374, 41, 'https://1.bp.blogspot.com/QiG2i0oWaG-OsT54zLOC_pqJlPqO3tDaJB8NaWO_faqN_KbfBPuqxqAKVA1DjzU_LLyhbJ5wJlESFvT9S4TIB8G7LCpQZCjgAZ6AJU99cIPUeA5WYaKQtm6qNH2A6FmEJPPZMBAVOg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1375, 41, 'https://1.bp.blogspot.com/1N0o6AqbHixqwQtqBX9fBqGryEljDwMBHPlwhKF3124AEL7-54FRXCJF7TxRzLMhszra6suki7Z_GS7iAd08-Rq-4JxH-8iMaQxmBh357fhsnPKFQ3jucBJaN9OG9jmSfkNpB6Sq2A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1376, 41, 'https://1.bp.blogspot.com/luJqsYxY-dbd5bYOvk4NGTgvDyBmm1AOKe9nCDDqlGHEf84U8ERpYSsvV_4Jhs9gG8SEdaEkhoGZYAfmJr_FDpVQXN8gdWQFBcuwnJvD57T84bNuh-W-1bTa-lgbdwp7Z4zFGEBtQQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1377, 41, 'https://1.bp.blogspot.com/q-cH4R-htVruXYj8z7LvBM84ZUU1HPmWZa6wntqdoXBsvoK_Fwa6nFsIfFBT94YWcsFLJdXdWV4M_pWsSX5KXiBsUtnyeqpQuVmbV5yLohwEMHw_djhMqkUwWKNyutT437oFpV-79w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1378, 41, 'https://1.bp.blogspot.com/BKvwAgaL17jbdbZiedZOPpFWVTQITC88MA4QvR7gUXyTMJT0FM6Fbhqs1vhSxErBqySa8tZ4Z_MYwgZHR803rr3hZsvkKvM8MYi0H2ZDdqI62dO-oVIrXeVcQgsnRlqFSwCjFPqXbQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1379, 41, 'https://1.bp.blogspot.com/817lErW_Rk7UmjJ5jKZuwkGqlFWAXToP6tdss-NagL4N0svHLCAJQCxcdeYOeLLf3R_bgdowEmmWoimb_3ghA4dwtL4WMEzIAceSTKcqO8qLjI7uiHtVRBjn_X2k6C4HW34kryqkIw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1380, 41, 'https://1.bp.blogspot.com/lx5RQSgEC9lQXP8f8LzyoqHHFhM2jb2iAhSY5mAw78x_5DYS2LkPHGBWMG_ZxOdF0jnXx4-KQwZdI-6JH6NTOP7LqAkha_aqtfPPRqtCqaGJeqd6rOEJ3-YimwNXdFNssImd5pylGQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1381, 41, 'https://1.bp.blogspot.com/RdaM5VCI3jyk0VU43eGZh8xurt91SeinU34DM-JhE73bJRuLi6A9qMPblitMClqI5hQqty_Yhf9Yw8VYJluklXPCozIAY7KHUXElOjls-_a-lhmmhiaKqz5vR8WHGzlkuIWwY1kh3w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1382, 41, 'https://1.bp.blogspot.com/jWyHt0JOSNSjW9X7JG4qb8fhNWnpKqoVTWExRnoSjkxFRE2xLQrf241zpVQicg5tTcjpeXZKyqGZmz8CRL34GPpc9JsTmH96MaW9s8hzon86rHH-JWcjFiLyujSGgTNeIwHRZFRzlg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1383, 41, 'https://1.bp.blogspot.com/RPNyAsXaaskgVNqHsjhJ7XnfwUhKOWRNlh-0EoR3FwtisD8oA2OonhweH3dRXDZvyz2a1W8Tj536m5Op6efjQONN2zyLeKa4WJHaqB9mX9PW5AaZipMjUUjRIFSRWa-oBgcz1XWZSA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1384, 41, 'https://1.bp.blogspot.com/WWslxkfhSRwbZOlR21G7RphkJamdVQ40R6zIbE9vlZaoJ58teOOM6QjsaA2AyZHGX802MfJC1jqWuuzv5GPKykO4nw50qLWbP6ZtfP-d2WDfauREuCLtP_gM2kL6KyxmH4TGsfJRbg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1385, 41, 'https://1.bp.blogspot.com/BnM0FEnB-O9-VCqvvTzREMnxQ9G8vbqYAZbbdgGR7c4ZusO6cJeJpIkUdlqVyIqYURA_mfOVBKXF-92kYbqHykgSIxHXQoSykNFqpFO4P_jQ1Z1MLmyjmhjK5HUWHVvIr2mCzPuhEw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1386, 41, 'https://1.bp.blogspot.com/101EcFpwFyMyTL9MKmpnlbmr7MopelBd6MvNdPVoNJCILf42yAfVeas7mqEKNCCcgP2E2_mim3EEzcCOylBFjdwx6TXp42zFld-ZJXJ8ARm42vuIaastEGtsqIHzPPPk8TbeiJ42bw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1387, 41, 'https://1.bp.blogspot.com/YozyDUdNxnx5Otr0gVXsO5ueWelCNhY1QsEBTY9M5mr87o167G-PG2c7A-OtUXhXDQ7zn_R26WNhkgwWFZNJ7vlHTiEDP0aLlj7I31EsUmilkV7LQmfn1SpZTUEVv4j8ufC1zbiXWA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1388, 41, 'https://1.bp.blogspot.com/R5Fa2n8VUqhLaKmfbL5HL_Hxdg95_gnw07DeipfPLqFSyzjSiCRYQt4Aiv_QwOGdJhVf7NRZhf6UgXJX_sDbp_0zgZzVN2hgGCc23iPU2YF1oDYYkQG3QqKGxt6qz6081XztKTaWcw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1389, 41, 'https://1.bp.blogspot.com/M0cMsFI9NAqSBRVN98PmJj9Aptx_B91ThppRa48FjOt5T0BoxDtW6u_0B0drcNiTikOLCSZNFRBjfkKoSZeiUkACTMP86xFgYIunCuqZz4pz3xh9QGTWaAQ7OHX2wmlo0vJuS16gbA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1390, 41, 'https://1.bp.blogspot.com/ssaxt30jdnLS5C-O_gBCswYbeOBXJnK_ZISxdgRfU7bgUwd8FrssZvlCTkzGRr_HeauKe5G_am7xA4JkbIhqwwHYmFUBBkZxA71H_W1JAfDoLgHFRteJPWkjenzNCWQE1JBNsNRiNw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1391, 41, 'https://1.bp.blogspot.com/jv1b0N5-wa0oYqwXjn_78gnk4CsaAgbIKfDeHdFs1PDVrT-DTxD5G2EcEpm3aGM_iGKrsedXG0Sv-eDbRMuVGiiqDVzLm7LyLghWnSeMSoeG7B_IfZ5tikvVWCNqu__EgxdtLT7PiQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1392, 41, 'https://1.bp.blogspot.com/LVXZHh2iXH5jFcW5oGGgNtemxhnor2aCaDTVk6r9ejqSZG0Ljnnjx7Qs92Jx3_fG1p3AS0-JTf3SpEnz0TRsPPJOShuo6YcL6tmouqA-XNfQHXbHy8IwIurqAwRNFGU8s1NvJqK6ag=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1393, 41, 'https://1.bp.blogspot.com/VuhnKi9zjJO9fGOw0CFuzDV-VFwLxXG_MBVf9WgloOOay9lgX5DapYVvs0n-Kt3EcCMPAM6315Flo1spuSEyzyOnz3-S5_IMdwmwNfduirYiT0PSQcNXmNWjuCCtQU9yJog48XSe-w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1394, 41, 'https://1.bp.blogspot.com/2vlAF1VGoXic_SY0IFcAHytVcBpTGWdpPm6pKkICuzJ1uPrDPxwbjHIPsOqwhN-ZQgaimpECs-KmYAugePeOHakDQx0wz2jKAojt3zDQWVZhaDiHg7Kp2RU9htP3WAObMjm4SIdRPg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1395, 41, 'https://1.bp.blogspot.com/r5FApOgbIFvHuEoFitEuLUJbxudM5pVluMAx7dsIHuY9X4S-78i1CjWlqfevnPvCHTUc7lzipBU6Vhc_T0YvQmCamWUIwpTTK0duc_BmBSWmON0HbHtMeLRC1dlmjBYUo9iObWayOA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1396, 41, 'https://1.bp.blogspot.com/QkiYfDNU41PYfajeLGg_9OOK-Dvde-hM72uJWZljfzmixLSwSLWwIW77Yaa88ub-YkqsYNz1doSAIQiGktfEFlzWYzCEZRV0ig4wtPmwOXS5utMAYvC0DEUH7G6FC10oyBocT40T7g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1397, 41, 'https://1.bp.blogspot.com/ZzCE4vWEtk0QyVz80l8a9nukqL0otrxiBxzKrcBhvl4UW6xFGumWaCeDNbe1WBnXpnoaL41mXoE31GL4aISleYp-XDOIY6jeN8y3TlrKIu4gsjdgKGkrkLHqWAB1OsCoqPTxA2GQ7w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1398, 41, 'https://1.bp.blogspot.com/wtSeBLhG4ABE8-0i2wwn95J_BuSfzZIzLsmhxy17dRedbBKL56E3UKk8qDigGjnA5DZast7RKyokNU5sDsAtIfRfbhoI0tgpET8yJxkFhHPhkVoCoVAmd9EHlQjOlaqr7lw0VRhjDA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1399, 41, 'https://1.bp.blogspot.com/KQlP0HWgfISE-VKzJTY6_fddcFpFeadF-H3ZqP2WXBYZ39A4fdBlKzCFVJkOYblnuMxCDlUsz2v0MxQQapCuxA6Laqiz7e8y_cmXJLOap-2QzCfoL63cPz6Jz1xIevGuvejcemzPhg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1400, 41, 'https://1.bp.blogspot.com/mivm7SHF2BElDUSMSLVPWwRj4w8lfe0X3papOfO9Kx8oL79ETuBmFmctXnefJs1cEKo_I27z5UG3SdJ8sz3xVllh_K_h6wXoWizdRzwBNPBQdKqa-QUg0SgWJfLkeYWh0Gd9R5Dv9w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1401, 41, 'https://1.bp.blogspot.com/PsXWg6VYXyBL5LX8Xn8Pv-Y51OY3zBJAYu3eMy8hqgzjrRH1_pkH_tjd1yh2btYe05DV9yxp41PnYq8uDuZiRt5vNkimXHvfRtmKOIRAHv9Sj9ufDrYWpZNVReyjUwuPDgzL8I2RDg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1402, 41, 'https://1.bp.blogspot.com/FBsfXdmnQrB4-J4O7FsTLuzEpvo4i0kWiTZNGx_sgZ2WqQSrbkbHcTy6Wm6SQacVFPLLCVYYRFrkzJ9TASBtFD-Y3pf-ScnL9fs0xH6nY4RYP_hT_SsG_YjCPjtyuC0YNzVR87g0Ew=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1403, 41, 'https://1.bp.blogspot.com/rGNNv4rdKMZsncSSWn6i-K406XgC-iqO42NY7HSubcI_dBSDA66PeXXfFTBKKTcys_qBEJV7Rid1hFe1nQTLZcfqFGZuUvu4TzdlZb0E_2kqkuaUXeiGdTGJ6lqjpLqoUW0A84lkzg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1404, 41, 'https://1.bp.blogspot.com/U9aB74NmHCWighZhdi0os59kFIIay9t6vhTGhO9wvGmoXKVhqXV4AeqYeVl03e3w3lYPedBLSR-uwxtlVFVe_9152kG3W3mSNWf2mWAwPEOGJ5bImTnkHeRtEs2fDKHwy1XKpIc3bQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1405, 41, 'https://1.bp.blogspot.com/IZ_D-uiL1Aar53HTlvKVk4oUJWTvaJlKfpwJkVIrqOLei453lnJ40lEWc5khvaG7pC1_Pt4PF4E-GtSSKqJJLJDsArHU80fjhiomnwQcANTAqbBRpBdYxjcpdc_TLjI-OqzemL30OQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1406, 41, 'https://1.bp.blogspot.com/jWk-i6gVrrwbntpnnv33aSmtobi6rb6gOK8zrMnzYUycYYxdtlpvn4W93DzTXELP0lGpfXvr0lcVyC9hONGYYuXw6nkCJEFBrmke0bHcRVzhSgD8smi6mMqTHVD5BhLF4TxFCUhS0Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1407, 41, 'https://1.bp.blogspot.com/Mpw3Ru0YRfPVAWahjeaInR8CgeAee7VyHNl2U5qXzRulLWpK6m-Dz5xFJR0ZtapOT6Pma5MphV_vvXHJ-ivVePBHxQ3zrrbgXU2bSUGEesEFAtdpJWoC_KSURFDC6dP3299IKj7UYg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1408, 41, 'https://1.bp.blogspot.com/rmD5VcOUAMSIEuY5HKNu3RQ6ucZfrIg6a3JyC2jV8M-PnK68jsj8zFyL3RkznVp_P50nS9EjXAvHaUxHEepR2yXY1Z2aYiM_JKSo645zpzT5I8ZFlYiPBa0ttOIIXfr1sqm72LORUQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1409, 41, 'https://1.bp.blogspot.com/SEhpv8q_ZtlXrq6WawOu2L9SpphgSAAfFTYHVKPwNl_1nretWcoL_AtLie7NDbqz4hQKsOM28wLZPBPQht_8H_r8o0N75FEU9vcToiYYi8UeKwFuzf36_1Abnqm0g1LP_ziH21MOEA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1410, 41, 'https://1.bp.blogspot.com/kVyhB8IlrLIusCzkC-GgXMCdjp736tYFo08892VRDJ3JoxDQQXvdH3-zqjSXxlPQOVDFLB9qb6sJDqfcJ4WR0v6sMMWBEaHn7BL8fodUBBLdb6PMt2C7uC-QUBSUW-UX4850CUdF9A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1411, 41, 'https://1.bp.blogspot.com/kEZwYEvzgibgvtrzh0CINNOoNadxQVNBGOSfEAnVIlrC2w979LpyGJKXF8YIkP7HkRE3USWE38GL-Dy62GGpp6aVJDFGaqZXJOkKlzGcCvLY473s2X8CZk3QMwqRCdrdYXSOFMvVWQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1412, 41, 'https://1.bp.blogspot.com/ZakbBz1vQYA3An3k3eLUHbRhZS-7ZWFMeW9g7eF3wUOoVzEoglrkLPwX7MC_3nsJQjJhxk4JefP_MNshEvklLGEV0Ep64jJNEBjLpwaopxi88YhMe_J0nH-FmfV0Otf2YT0N_WRWXw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1413, 41, 'https://1.bp.blogspot.com/e7JE8QoYEzCpN7MLaF61vJQx7l6knJiuW3cmgG0NtnbsbOT1guD2t6DbQXSFA0bTEObHtDTEE1TjE_x0HThduwJ1UTmmIFtLEeIGnGxQ48HkUiSBpa26PHCLk93CFk_dJImPIc7gzQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1414, 41, 'https://1.bp.blogspot.com/EkmRrxZ9AE9_y_Jlfk-pq6271RaTU6FeWvlXgS451eMiKOe3izZdiUurRAoFYlC7A7BAoBBritG0Acb-KWRqcu5VmY_WKTohy-ZcgEscY-KckzAXzN_weV7RzZp4xwXHulevjIrUEQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1415, 41, 'https://1.bp.blogspot.com/Q73YdKQuhqkgdPiNfpt33Uws_-OibiaXLARY-PL3obOyrYU-Ve4GWUATP9aF0vYT-h0qpji1tAL8iiPdF-SbD2rwOge9HHlGec95wqSX1t9X0dn5OPz7YD58Sar4doINY0WZuF2Ipw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1416, 41, 'https://1.bp.blogspot.com/E-wHfWOdRKL50aMwbsjQGskjLKePHZ2vpwOGNtu-7ZDHgYqx1s5Z6kUzCr_ia_uQIT6sXm8FgNL1IaUdd7HvQ6ls5qSmdQsDGH7H7kl4Y9t710TT5MYhav1asIrLNfFEjphdAzkqmg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1417, 41, 'https://1.bp.blogspot.com/ZspCVanvv_OMYfOfRIkQqUJkhEC9Xa-WUwr8ovDbBD7GK4ebOGP5Lrq6nqS6qIcCpc_U6N9vMNq_URmLMRJSPdGK_RTZYZkJr26vTEMqSIK_K3X6P8Zk2EEo2Jb6MQdW1YqzVJY3OQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1418, 41, 'https://1.bp.blogspot.com/equlBlunaKsRiNxpXrlyMJ6MgGVRac1PTaIgSt_wvVo3_fCtVHU8vuAdWsGC0qW8fEp7F5nBOCuL8RaK5lSm46J7tfebAU5LPxtkhWPwC_k_fjY8tmlOs2BIWBppCPsnewRXLT7DlA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1419, 41, 'https://1.bp.blogspot.com/dAtBNhao86ztxrXCsojoCf5EAhIyDSzXti-8IPl0hLEAo6jPkGWtXO9yDzD1XjiTXwHsTCo3ypd0oDkDQ41NsHXajZYxcBDR4AcfFJxho8H0W9mrHpEtg4DIx7t0_2F5jt41Y4EnwA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1420, 41, 'https://1.bp.blogspot.com/qh2pOl92t_HKe2Gsz6A9OvkX_puQgVNuP2kQz7ebkufRy4WUx5nGg5FUbnFqoEQ6oH9q5tp4aNBkGBdT7kDVrH4UEAs9Es7aeEaNBFfkIH0n-F1ylmGUZYBiE0Zfn7zvRzagcQ2_iQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1421, 41, 'https://1.bp.blogspot.com/8z9b_XTEfmZ0b78wClsfuIRoBpx8fyhgbpTg1pF3hto9kG-Gmd2QI9XMwN2PMBCvbOfqrzi1Bhsjr_iS2Q99EBFNf5lMgMMml3A7gVggZ307BduyH-Xbc0awrQfE96MN8shkMQXfWg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1422, 41, 'https://1.bp.blogspot.com/8f7MF6nMwDknvdI_wga4wdVZbLRnCrFOpFhudiR6PPkzlBs6xwlF0l0Q8JvQKW8sb6Ms4KCIPAVXvdepdN2zXYwxA_z4OwVBVSxqMhvQ_eua_WpVUlFC1P--v9TmqoOrJIsRNON1_w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1423, 41, 'https://1.bp.blogspot.com/1QwLn5eRs1rliBQDGxmLDyiF6WCb6VZD_vuFmq-Sd-Scsm4Qce0lCjylZYyPBcV7CXuygAiqcIV2fVdQGWS4m0EpFmXRVn2AbcJUGK10mLoFTG1HfSE_apb0etGFnDysA5Bx4KDEgg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1424, 41, 'https://1.bp.blogspot.com/mKNQU_8eqv5VTqD5ZAbzbEnhatktHsMnMRsPMgBLWj_3sHkSv8xgef1chVubHvo0JiEkm-mdIuaf-QFzEb4c23tU-cg0Cm2XD1aCkbHWG2A7KO66VZEE5UCqlJ2hoNrTtUFnij73Kg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1425, 41, 'https://1.bp.blogspot.com/PLLmHK5an_6kBgXDqNsBmnxSZEJSg_GJpO77dY2b4KTxiR3-MbH9CubiAeHGswDQBJzghErKWjdyfcVH4YLJ57KZG2Np80yv0VzYoq_luePbpMl1Yh6_RFEZQoCNgvbEqsPxiFcMFA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1426, 41, 'https://1.bp.blogspot.com/oW67iguK5-kXPH44WRcWLVrMyRkDliZy-dx3K7yyHBPIbFPPH6aQsuxi6752xU94K6UByzWVyowMLyePb253OEJe_6ZHVJazcELdFz_raPAyyB_NsUKT2hUbEowjCaefmM4hbie4yg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1427, 41, 'https://1.bp.blogspot.com/RbRWPOaFegmiz1Kt-Lp_rCK-CXRT7LT1--gNuq4ZTBHsgTrTBo4bI0Ny4ZoMD-Q2B6ny6mtg1FOz-KrNDXhqe3FnSgCUZmIQwoq_oOs6KYeSVgtQjyS6fKs7fjqTh1JWSZDXQyqPQg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1428, 41, 'https://1.bp.blogspot.com/-UcS0PR5cZNSTPNlIZzgOT5aQ667H_D06rl8czJkjeDlfxXjPMfP2y0MbGwibLsphl222QHmTvIQFcIry_XZbNjNCm-jVMrdBQ_wHu0nozAiqaw4rJYqHNaOCNYJfZqRxaV7UyEVow=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1429, 41, 'https://1.bp.blogspot.com/qv0ITPjAGs6gcI-Z82BE542C0YXtq29LQTjBLYLljXejnh_U0GL7wpFOsgRrMikLvY61nzPpzjqsxFe5CTujAvShIr9cpasWLr899Sv3xsvnxqWKQnRg6kitI_yFRXwS6WWrE20sJg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1430, 41, 'https://1.bp.blogspot.com/p9G6yxHYJ7bRfglyJCAE_r0M7e16f1cjJM39zOnCK04--6FRcvGqd2GUAzRhy_P-GCuBQ6suroBU4pSXkIw5Bi-NO43lDLGBAg1DDsP6EaaUB-2ySgtDUwpkq8MyDOoSVrKgCIYy9g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1431, 41, 'https://1.bp.blogspot.com/8DGk7vEFTGGK-xdnEZDwSZiEBpCMTNGA2SyKKM59yVBSORyr6xE2LEu9f14hLGBwi1HVeaWVNYVa4mGD00WrB9TaLE9fOd6__JmkC16OmdBu9onXUI6xVdgOM9g3KzZlVYjO_Q81JA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1432, 41, 'https://1.bp.blogspot.com/2cafKftWUJCyYwH7C15PWQX9OvzqP6EDiVDvX6v8E5i8YXvTO7aVgkXyG00obZ4FEWSpWfgVpsfz_4BiES7CY0DjfkVZRWOlOQAXZ_4f4x5MZLeeXoG0r68OsyJyOfKuXobeBgt5Rw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1433, 41, 'https://1.bp.blogspot.com/_kWVkyAL4kWqCkwZQrkHme0qFte9ks4WHbZrpm4TNIhsMO7SYypYpssxCJKh8B3dv1JhQrhHeaYU0lttlFZ43s1uJn3cdLdiArkAHKa31MeDpnszSwd7WbnelOqoJ7Siwl2VgQ61aA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1434, 41, 'https://1.bp.blogspot.com/2LzQrByERvs2OtgGyW2x3DpIqEVpOM8rlF5IypvN0-ZV2bMpmCcBrx-qTSb6mrbjiNAcNEdp3iu_gZyUcfF8KZ89Dr_3cZcEurn96NU3blszTbW0i7r0os1O_qIrPrkOMn2i-IhH6w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1435, 41, 'https://1.bp.blogspot.com/W57jbLXylgvzsheurahVK7vT_EHWuo-xliaEwB9FYGj_GRrLNEqshBStXhaZ3ZbIRUlDCGJSXFAWbR-2vrgKIyWEmHyTxRiHRP9lPkbMeJxuldX5qd9LbAYYyTYMyQUERgzzL17rOA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1436, 41, 'https://1.bp.blogspot.com/CjumutFcdRwz8HDj2-HrO_RjODjUFNy8_8ZYi070O9knBthUiMDxovI0Ffd6wBvWEZMzeIEAMVccKHzGfZA-E90HvxE4psui1tesmMmbdIrL51Mdgfka77B8gXj-RqXBg-z5tilTVw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1437, 41, 'https://1.bp.blogspot.com/52W858aNFIXejJhjYhSe9f-c4ib5g02YwufpfHc4uStj62iYf6emp5jB6rI_n7-OmKeXMnkMZTeWRw9XjlGcsHwcPCoJwICvoldFGViO2ySIHH8hyx73GOOF4bIq_NriDbwjJnnX-w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1438, 41, 'https://1.bp.blogspot.com/EDXRofZ5OXyjwn7difm6BubNzDUlctwxtJPh7Z9mN_7fMLgLDq3T66GbK8PCtqLU5ElGIcX_g4oXulJC8kYnhPCE2FxZlyGIrKIMGjDo1xA_wLhw3RX3usLjmXhrgNLXnh3Fn_VUwA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1439, 41, 'https://1.bp.blogspot.com/otBipqY9iVQDxgDA4f_nBYMqRwsouTQ5m8AjipzqF-dG1Kjhz7q2s9fEUXmgqQ0pW-Gl5DZ7y9L4lyRkw4XbFWhQFMXyxc4ef8gIfJoTuad_pih_xN51dc7Z7xqLv6VcsiNW3CJ88g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1440, 41, 'https://1.bp.blogspot.com/WGA8nr5KCkQ1RbVc7UhK3bZlin4XDURDtFpWluzkD-iTHZ9EHMTuduxq0Kf25mDXYvAu-HJBcguJc8pH8iTgbuhWdQdDqd_rsoD0nPdDbg6qJlFfVuB9pHx5DPFvWdPR5wZi7VeTuw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1441, 41, 'https://1.bp.blogspot.com/JkzFju7bOXPe0STahE5JNVocu_vvhxW7CcDJUU2I7simkBYypzw73VYpEXA3b-OiP3SGqGFRLM7eSSHYtvS4QoWXFvWzoaytWwEP2aY1w69RP-6LGL8Qh6l2zVXkSQusBlK3zz2QVg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1442, 41, 'https://1.bp.blogspot.com/Q9uSreCB6RrwxkGeKwYV0ilHjGV8ODCfG7Y-KF1Q7bgbcQy99Oww7tBqmxyZIgtmB6rd6ImuOsj71WllXJ_8f_dnJlua9vJ9d5Qf_plaktiGwmZ4SuatfXQ54UPOK8iqMQ53vcUbew=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1443, 41, 'https://1.bp.blogspot.com/h5VCUKgXSrfzPX657Aou4oUV6NE-c_7L7x5o253MuU3vDP_6eoTV5KSBOFaOHJxb2t3wMwBjWIPGEsF4g0UsKSh-qMMhsHXj77dTJEdch1hyeul3ZPNbauoPtYeqFqYxBieu-TRQdg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1444, 41, 'https://1.bp.blogspot.com/hv9UdsUrDfC7d9yQhWFNGkx69Ae2g54tVaKLu3xWcyPi8i9FwUMsha5Blp0oPkeVz77Vt0W0yFnBlwMrFz09xUoPsqcO4JB0jiuREQDlMq17EDhvvj2V--tEuhRCKMJ1hq-20Y7gVw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1445, 41, 'https://1.bp.blogspot.com/QY4O0jZYQlHOYT8k1uqLnyG-YPABv-dBPm4LStxSN5ruiCoSwZNEMeTW8ydSiAEgViBpCoDleUKcWshiObU_B6_LDEvXZaypK3DLZD11sDEJxhzY-NcHKGJfj0jWQgjZHk24IrLl8A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1446, 41, 'https://1.bp.blogspot.com/GcNxk2Wh4Fgo0IogE9QTOK07oFcPGTpjMTEfLr5F7m4FS6h02GR8jYhV9xy5HJ7MkrZoPkIz17M2FeGVOfER4UMAlrAmtIMgdMKphpvDL6ViaJu0eYuaqtsbq4jpUI44y_yAXy8gew=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1447, 41, 'https://1.bp.blogspot.com/2gn9mrj2GZECec647z4Wnnf0v6MZJWpC1T-I29Q7q8wNPWOHYkxabymjuu08_d99hNX_XP2zGbuYkZtBVDEuh1iZS5yvpYQ_MT6kniMRTq31rSYdU0X9qy57UPPVTwJNH9t_BTy4Hg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1448, 41, 'https://1.bp.blogspot.com/_HBly_nb0jQg4cQ0nA8AeRvLFLtdIAprXSFqYoko0m5bAgxrl3Qe97WEQ_P4TkqDIndPrCwFrl3O5ax9hWm2czr3_YueQ8aQ2TL5visUU7rMtbMI8Wa_umhIXkiCft-zHoiLkEqwoA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1449, 41, 'https://1.bp.blogspot.com/f9tER-Zmb9SgbjWlGMzivTEP2tlc5C_UdwIj2U8GfriFw29PpnL3qRbumW3uVOhZ-EU4Tc0pSyRh4nRXpYcMic0lbBYeMxH8p7nELbOyd3ZzYgTmc2bwJ43RZfT7W4TCF7ZkK56bQA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1450, 41, 'https://1.bp.blogspot.com/Zl18KNZCftKlVLiuCknT78-dh4PUHpNX-gZLr_dTW2pCrs9LKXR1Ae7T-VOKfDEPPIIb2HQOzrTaKlPA0H09sg8nTOMcIreRvOdjByDbLVAVvzheCBEeKJC7J2ZYuBsFTZMXE29c9g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1451, 41, 'https://1.bp.blogspot.com/3CbfhEW3jSrmcj1Mhmh-CRNdVRM5PjrOUYs4aFeuyq99ypCgGkViixhUFZo83W54NCTm28CHDaP2llON03HuYKmzr0ig-Q13rg0ldvf5malOY9kx66JBfQU1co5orHdDWca2QRBAWw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1452, 41, 'https://1.bp.blogspot.com/nAzurrcpoEcJZ3h5TghMoBWhPbzzW1Ey8H4V5FdxKkqzW5KFy5MU8PeUAOe-VHAupTn0bwce7aXOjFkH-IzhMENXSICzfmWtq7VUGlaEmTs_IlJHagb7ehzWrqScgWsDtXq7pzXleA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1453, 41, 'https://1.bp.blogspot.com/hTg2s8xwpRZIJ94GRX_Qvvawjb8O-cjxvrkl2XlBAaPBo6AmXMFtO7c_fD-8JmnJHZx8SjFiVJohbUke1dgVxUSBvV1F53Zvwt8LW0nJgZGiWqKuYMaJr25No4FmBmsxY6tVkiaR1w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1454, 41, 'https://1.bp.blogspot.com/DGjnC70UkRjNqrwVjAeT9wn2kErkphWOwiEItOtxSXUTyZcJCjIsRNetuqBbm6NrJ_VdburVC_76yUrQ8Gau_pnyegvyBvJ3-IDdTpaNmNCeX6LvZ4DvOPWTvfJ5ajnXzQ_DzBpFOA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1455, 41, 'https://1.bp.blogspot.com/Z2anf0GJWlKvMRuYXkt6o1RChtUBnSczzau_816VywpcfPW6srrTU6y7QLLPQUVgZo-N58-vcU4wO4BPocjPhVjQ4SXiL-W0O05sOrTFwLEerrmG9wHna5Gozvg2YBiLqOYJCTTD4g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1456, 41, 'https://1.bp.blogspot.com/eu6zltS2F-PaK1R8QRUac3fbUrwEp0es17ZNmoyGd8a4Cvl8LcEQsCbjH-svNZf6BL1bkhuyOQr-__ObbGciGg4fsSm7LfPKXUJr4TN7Q1rbebA0bzw4twgRewL6a9I8Hy-DL4UydA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1457, 41, 'https://1.bp.blogspot.com/PQeAg3ltw45GFWglCi3PeX6HjJhhuJliqus_HW2Lp0cE-_FibefSplIEO4kEAjBnVIB_3Q-233kZ6vfUJVr0COw8iT53UHBhDP9RhXgdzhJtbaEsLu-kd85lb43pe-qhDNLk3WrR1Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1458, 41, 'https://1.bp.blogspot.com/MJVtErwnOf35B4gDufPfxQ1Mo6TWBI1d1xF0wz56eZ0RgLm09Dm7bpBmOuM6c09dR0agx0J_yRBxpa6sgfkB8iieAjK3s-vYP0kfKQdzS2hWBRRST_T0v95mDrf_63coPRH0256-oQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1459, 41, 'https://1.bp.blogspot.com/ijK4pfFxCCmAddcozKmGFQ7QXS1ycnyIikbWPEK_NsY-ie14fhslDGO24S-a105m5bs_iMxWb5p7XKX3KMQMhA__L35oH6IiftaPPjZimyQRdsEe6Nm7tITqeIzIG5bRIuyycbmTxQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1460, 41, 'https://1.bp.blogspot.com/YKWSR4RK3AH7f1Sja5G8LZSmo00LglwncYK8lJ7An4FVlPZHxotHDxDczQeV8CE-rszkNFpnHYECDvgZgdijb7iKIx2ZfJqs5q2Fm5QwQs7sGK5MVUpZAvTHuIzSKkC52iYb7wZzyg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1461, 41, 'https://1.bp.blogspot.com/6En457NaCZw5xOEP2epkrCDpstmQEzJXaJgiyWtKbc38tjjavFMb3tem9RCv94VZTVqkqwwwxf0xKzmC51xekbkJG3PaSa10h7Ogrfh5TfMtZ7_giJ-hluGyj0r1OVhykIQodihVsw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1462, 41, 'https://1.bp.blogspot.com/iHZQuR0MDyLhANtbuZUHjGPlKUNffo1tR1mJr57Kze-YsAaFTcPDiQyr_IHRBhYgBondwDa5HZIWwVfHAc_zRgOXWFWCwsGvX7W8WBW_6Qn-PxVGqp3luKYOTp_hlO2zcWrCwTTitQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1463, 41, 'https://1.bp.blogspot.com/2qSsAZSV3zL_CyZsVO02asSIOZnIw5dIJpYJ3OdxYLJH7KdRqK_3t_oF2CP6JYKp5XYBSppJFPlvdFPpj8i7ifYa52wJXt7TvHwHRFEnG-XE4Zh0xB-NRiFfxFZz5kLm5-uKI798Qg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1464, 41, 'https://1.bp.blogspot.com/l0IB0QsMEvAW2a82vs5a2LtD4nyXzEAUa2BqeanfuPzYLhDQM5tn_5uLEDoQkv8ekkdXzl-iUY3ip8S9POyOlyHmygICXXI5VdPZOKGu0o4m0L5CshHQiN7tRbOyvM7Qcrf1zmHcEA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1465, 41, 'https://1.bp.blogspot.com/qoR0coMlEciAEwMgT7BQ4WEA-UBftWtqK1tre_tC0I8vN97Pt6JcScJzLgqW4yoPMAucSwK19zWeD31broGRI0Dbts_kTA9Y1SxR8kDgMR5Pd2fp-bW5d8aJZmNavhaQJd-H8k9yOg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1466, 41, 'https://1.bp.blogspot.com/_V7y0JNgF5Bw6hBtls8inPXNqctydUxjc2cXaqDlqV_Ki0Ahs_prb5uPaVOac32xfFetsl8r8rPuPVE1-gcWpVrMTmvrZ3DSjJG1VCL3LhX46cyvQHQvs_BqjQGggj8I-tF9RYNmag=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1467, 41, 'https://1.bp.blogspot.com/pl_neqbgG5C50LQ8WPP0cwrnrpxvpDTUFPxO0O5JBJhXtee9NpB2HxBmx3oE1o-9PJXA3ASnY2ZrazgunJ7g0Vz4d3MGvDf7BXa1ye8Xj5fGN_2iwAgSz9WQG-IR-gQCAKKfXedKNA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1468, 41, 'https://1.bp.blogspot.com/Bo5JSWgTJfP-RAPKswLPSan3JANhxqN1tEQ99JRWl35rJXZhjXLr5vUcLZUeFiqFFMNrQzNvxz7eU4HLy1-rsd3e7wSoYd3rCTqKsn6Uzd0hMFXSJX1P4ugli6aDHPlQu7tZV3H8HQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1469, 41, 'https://1.bp.blogspot.com/bYOEiJD3Z-OwyVDkAyXrmGpj5EAOGU-A8hOcClui5bmRS0HoGdJun0EQilVPeQOryX1L2gk1m0P3Ty9oFF0pcQDererSErKqFJRToQkQnbW5PaIHDrM2-_JJ0Aktmv5nSQ6kN8RxEw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1470, 41, 'https://1.bp.blogspot.com/upr7NXGewjJPKKlid-6XuDHgpr0p9S0IndHad22laLwy09lUTpHTxCT17R_KCRiPPpTzAKsk3UWLMhNmZ2v6GaAtld08A3SyG3N61CoMW5FVPn716qiS53jO2ct1ITq1P-N9VMASjg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1471, 41, 'https://1.bp.blogspot.com/snowo7Kfrnijghu3xCFnLsPXSIfxoaXnnVAeG3H6TJHg_9LmlwfHcmvRfCk9YrpQGR5YMXRuBbB86TEaBUSoS-HFlTIiIbOr5VG-oi9U9YValtr2GKZQhCdCfFXqqdJzO1jPGbLVGg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1472, 41, 'https://1.bp.blogspot.com/MnZ8ZkpcDipzyl_2zojVTbEgC4hHmPksF4qGF4ockmul6mTfBA6zOd4msoH73lzQCtl3yZB-HS6hQPD6TP2IEHppK0bVZz_zuqT2x-xzVlIeeSw1AKX-tI-ITCqcfRAUPMqosNOQ2A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1473, 41, 'https://1.bp.blogspot.com/2a9dry9DLT-f3_y-MepapqnLNoQeLJkD2AvpcUhxeAQnYZQb7hEwl2SaFYc4PFJ00pl2mPvNBqZ7_DYwC9XDKtyFkA197D2XAKzerlTxGa5ylwbJe4kaP5adHM04NUn0fSoqjp1f9g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1474, 41, 'https://1.bp.blogspot.com/sQP1OqVHbPf2pfHvG0xng44ANmfL_F6byj16nK9hdtr0oFp9nG4QU4INyhbbXCA-pDZ5uYNLCFrHJq5tr6xGoXoog0vDRgX4B0dK7Zslaoi-t2OdTLxirV5O15-ELXncctJcp1LwOA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1475, 41, 'https://1.bp.blogspot.com/6SmNXynoPViuMsJ14HVyxDPXSPjnpiuXMUSHFGOmJ6deRrg6X1CYq5qNmgapiCOmw1iJNwgr8sBaoEglfLgNvQ2eJD1BSiBUnlrAtAniYT3pj8h7UaTAS3fi6h05E9CBEbFyPbSw2A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1476, 41, 'https://1.bp.blogspot.com/4__wtkH5TfsuX-9fBQ-fQP1jyD3GEUkEpllpHMvlSROhNFP4ahOUIB9et4iEjIStVDE1hETiaOGy2bYyWfvo1SCriPNh1z7SOOFWG42DznsWL6XRKso68KH9KiaMiU9lIlFVifM_OA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1477, 41, 'https://1.bp.blogspot.com/uRhFPP3wvmFtEKpeeNPaqPAlMhe2Lgoj7HVeFESRnaALlcOOXXGNQWGAItxQrYnzNURDPMBjGyR0uFDCTSQS1rvCNQyZd0zoiwpv8BUa03fdUBnJZOedOT1fQY41VtyqkgdjWx7bpA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1478, 41, 'https://1.bp.blogspot.com/Ka5NtstVm24y6f7g7Wc6jA2nR1VrDeJEfjAhm33AFQ7KKrPtEN-_eUVwrUg9DemtXlFTMIkQPhrLgJy8w-VAzL-dMu5RUaOXt_ejYyl2d-CPIqB8Q3uSsy0aAzf7TR9gmbVg9BMFQQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1479, 41, 'https://1.bp.blogspot.com/wGTUeRjPZLdg6lavnKLXRNKgHSHoboNrF37VL4DKC-K6wlRIH_XVFZqmnD5bmLutz70UYvyO0aW4oLYh85ofOFWSGVjv8_Zrr5mTbVMIdftZ4YvL8M6Yr-Rxp_jNkIoUsqfSRcP2DA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1480, 41, 'https://1.bp.blogspot.com/RfqxEq-mv9IJ0R0eR-X7BcMCh0rR33iW5k0vIu_G_LojPi-_-TessG6PB0B-fgyb29yqZj9xC-9sRayBrAfbrLbzWItT8o-Zo-1USPgoP8dn0ow5TN9d7nMtlwl9xeXmVxFK_KjeVg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1481, 41, 'https://1.bp.blogspot.com/IVj7ok5s8fppYTZ3DifC7Stcu0Gz_sFFaV-ClPU7sXLRGVTWKGi8HkNES_IYJ_Wp8M-K0bfI4LPHd80XljyZEMBFL2x9U9WETkMyOZk8dVtlvttzdqfwfgNJ999eOR8E3V9Q0yG5pQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1482, 41, 'https://1.bp.blogspot.com/LknZsl8YeW-0N2ZOtXx9vDiLoO5AGEKqQ-Lqom7b-_dz4eS42jLwJada8C3rNJtNI0dYXYYD1itYOOrTuVgkR9tT0di6MPennq2ZApjctpVy4z4_C_4zOxf7L_A7t7kb_gLkQRIxkA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1483, 41, 'https://1.bp.blogspot.com/EkfsoX17fq9cne2zAZ_adYZHN6s8IP9FtqaKBT8A20FR_EOZRux2s42ucNTBYYsLewJAa-ySagy_20AA594uMcoHSzt1570rUwJOkJdYpF5wsKp8W8_sOJW0RzPf5vN72o1QWFTapA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1484, 41, 'https://1.bp.blogspot.com/ztmMASJKv5XODtrB8mF1ylBKwcS0ojSpl5wLxNj7aKRz5beCxIV7houi2JIiwHMKuw2HCePsWKngCtZEA8N6fl3mL8y6nemcDy9HrYERsfSlvKLACfiqiBZxHjlsu8fTUHTrer0Nag=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1485, 41, 'https://1.bp.blogspot.com/FI7A2_Q8MgYg4vGTh1UpszCweqEHhBNPq9y5_gL7R1XuYQoBYZgZAcrBaodXen4T5pNCLSJhhi7wwIyVPNjxuZG-HYLDYLxqeXIVJ36MMxJeY8LG89m8Pgtcll4ilmGERT3SfBEXNA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1486, 41, 'https://1.bp.blogspot.com/QcVCwFky_sv5TauMwhVVFmYBJq9klAUv3wfJjfB_fIFWPtPlUh7IGDPnrvNd7jQs7B7JLhf5FXfd3fdn_8RRyv_ez1LLtWJMz_bEmrDo8bcud746RP4e9T-TdQfbPz-wNro0W4eVRA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1487, 41, 'https://1.bp.blogspot.com/Zdkvq1-dAxlO9NGXz3XZdxAcrtvHqFvQILQvZjre_LzQMXh0nRYJEVu6WmwH9zAn-EOdNgmJ88Rix15L2eu9OFT_XHiReBZSb2TAlysCZ3p0cH9wzH_NO_Zmllw4OfuUFE7gnlKUnQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1488, 41, 'https://1.bp.blogspot.com/1Ch5tf2W7tip-258HxqUwo_dD32H4ENUqIbl6R1xbDvf50TPy3UARusYSzb5w65HAQtlkQ9kE3eIIk-D8Srfmgg_Pmy-SSDeIiVaQThjKlbMtJ0Sp1Um3QCNyCtp1N2mOmpiKV9JEw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1489, 41, 'https://1.bp.blogspot.com/A5EJTj6mRLG2irwed39SwMrFUSce0obdOFwpJjEU5xszAO74mWVzqifdIb6m2FLzXLTbTvpS_apR9FXOO_KRcOhvFTZbiYGTtrjFPs9qG68eP-O0OvZWs38ti_2WYUuCcO2j2x5rQA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1490, 41, 'https://1.bp.blogspot.com/C-trWE1QWPPth0rWaL38xlOFtGXZj9yRj9uNSz2Yz-Vu7Kw4EgsEE3epcKvJMkhlPm7SF4DBagGAVt_qSdfJP-dlEzxII8U8QJF9xqKh7Fvj0fy9AWiek_TdQH2SczzKg_k40UhBrQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1491, 41, 'https://1.bp.blogspot.com/HbsGd_pEJmj8vS5Z4KAwxCkXcDghrNKclRhttxSdkvDhfPIaWx2Lhy_o1f6Q0pmZGjhWgIO1xrLhnVUbBKjeJQ4cZcFz_0Y-7WgIXWIhx24DuqTzEddMXJa1riMFFrwpT7N15jk_KA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1492, 41, 'https://1.bp.blogspot.com/UDZR57poDP1apMOpAE1SrZeRSs5lz5OJKkmRA0eXs35kCgHK9CPJWR8BDZcLbqz6RBCvJYLWDEXrNhIc5rMVS_mNvAhGTf08-9TT5GmwRn22WH_DO0DfAnIIDixzPsKetw-Cc-K8HA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1493, 41, 'https://1.bp.blogspot.com/PUZPHJVdPmlr9BZMtl5u0FeYkXM7jIWIvEzR4DmhUJ0OjzUe--4xwkRQ96AbO_82TDmZBAlwcvGki_khJVXlLMqyK1lpwQNq2QwAhXRowvIUmh7mIgkjeeBfpsDDEiJlbCv5WaldIA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1494, 41, 'https://1.bp.blogspot.com/YytEt3jRYrDmatBGKZKUNyzAt6n_7ee4qWXmzYXNMPeHBLXOIJUHL3PI5MDTAz6Vx_8W9yUl-fVLyAzjJxNnWhMEx2tkYGQsr4FjCcdWejUb_67tCHYfGAoiEarqq3F-1-7P9AqUzw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1495, 41, 'https://1.bp.blogspot.com/2gpUydGpz7xGStPwvmwyc7Y1twlCo6_4ZNFQOR_YowkJeDkRXAfpxZjP5HfX8Xnepq_H2jlBT_kgGB9FlZbaq_RYfK2yxofldSskGwCQm2uz-uG_oMEvRsP5_AoymPukEzyXYm4Dtg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1496, 41, 'https://1.bp.blogspot.com/K-83SqEWG8hPJ54GlndDv-ArjkpCyOQArfwhbmahxSlgtptErdVMsJhGJSmdDyfBkZ5jIhU3VxbZl75Hxt2QKxb-LGSByIqTGA5hC4m6l_2Z9bcLd7DsFoxTZCZBgF6y6vFkkesglQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1497, 41, 'https://1.bp.blogspot.com/8IJRj2L9PogkkwuhbNkCkprWjXqj6uuMJIuz08CZUwHrQi-QgwOEnkbnCP-CEhl_v0HClra9Qsjcy3_WzV8huRHAxEikAw9Hdlq8z7gyLcLgAdyVd7CvLPHytMRrxVtgrMi2UvilUw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1498, 41, 'https://1.bp.blogspot.com/LbB2DR0o-pYOZi833kruD2O8BySFFb43QX1goLEmEnh5d2gk-ly3kI8sv5pG9NSLEAg1DgG37kCuPZU3pGqNG811JuRtVcNpjjXrAYUFnIB_BA5dlHs1x8wISeSEjIs7sqHEl3dveQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1499, 41, 'https://1.bp.blogspot.com/vYGjz3V2QbLx-hftzrEPGe_ZvjYSGGQHBCA62OUpj67kJtsViP7ivBzBEcHwneldUefCUL8vAZPXYnby5CQcLA-qPOyqlwbtLQhD9_MlaqsMzo-_KB5WADE8x1sGu7XjDL8UuFuQNg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1500, 41, 'https://1.bp.blogspot.com/UzG9mE8HPe5vE1EifYWxIYUQ2Ou_VmHNbdzMGtd_Z_lBqQYqf-a0d6URhaxxRgE3TCyh26G-tbXWiGsGNI_2AzSnX5WUW-0HlhbT6ve-RvOalj23Wev9GOWFnKDZ7eZl-wTkBLKiKg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1501, 41, 'https://1.bp.blogspot.com/Z5MH-Kls2Lk8FjC99QMMUgHQoisxoS0Lh9FCd_UF-oQ29hZUMdSxmv346xiq7rUbS0N1rrmjs4qt7iESOPGrp_Dmr_W_jB2EFvnZekvU3fysaN9Ar-lLi2F4D4WLTchL3jTezPy9mw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1502, 41, 'https://1.bp.blogspot.com/kokHvNwAAz5llU1k7ib4Hgs46_AaX80df40EoD77r5CQL9CT6MGHOKw24dygmVawKQ6owBZtZdwnXJMlbPnkCbRD_x6g2dm8rX4ds-GlaLccvXR9Lzp-0HLuAJ5VAiVdI3pMroAcng=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1503, 41, 'https://1.bp.blogspot.com/ISNqRqwUlaBFr7n7b10fHyjdllk4kwOuYZq-IpndJ1OITbk9FMUg3Iy8cTJmOAc0C_OQhzvJOtf6CKk1oJroHz7p0Egybs5EHvygOhMCp0pOIXVKnrBIA1HPIx4X6-NGEmV-200cpg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1504, 41, 'https://1.bp.blogspot.com/C-364WmWv-kRhJkKW3OUFdhsVQVKfGWUfIEyUuQoqsT5qfDhX71A2KjAjtOxVNG6Z48s5CHTglR_MXORBJeXUYESw89GAMRazDPCloXl1QXWU4eoZQNcTkdCyy7YLivN7T62CzVf1Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1505, 41, 'https://1.bp.blogspot.com/2OM3IGTd-2m0CwLrirfHmg96U38rly01Si5kKo1sHoKaeGeygqoZGYKwTsCCnAzbiOheAfN1fjnDnR3iWNlYcbntTIEKaIwU3_nH9GBzBQSz0NaislHMoX11refMZGrrAUnMPtSa1Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1506, 41, 'https://1.bp.blogspot.com/8W2wDBpSMRJE3mQsMuY28dpI34pC8A4-erUhsBANS4-BUeD7f70D6guNOcbL7ZsZhrIjtl_msKBysVmTGFwILGxs8ETw58uOoD0aVbScfqnCHnSnW6D9__2N6WPk4fLJJ5E4e3HKvg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1507, 41, 'https://1.bp.blogspot.com/mg-OL7v9fE0TwKDzoX1jMjVuUD4Fe5u7lwgRqmiSxPXVc-nO4I6ZG31KxFT0IfPT5cS-GGsHzf6alPue3Lj6BSybxXVHh2_uDRW6eTz4Cd0JLhhmJmYSAcOGu_GL21txTuQoAe4p-g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1508, 41, 'https://1.bp.blogspot.com/Tk_Xfu3LtJduhX2PBNpuptvX3x3_962fw3aeORwH9ayMupHVADnJo2ZIKH-Y_FNqHJ0ZkBhw7xDW3WbNdExHo5fM3WBxn4vgSnEOuZNXIQ9AzpvRzjQIKhv-mM_kr2fPS6E4CiVF9A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1509, 41, 'https://1.bp.blogspot.com/Cbqa4XBaLuMhsVFJS4eR2x7Lnugy7_plJnLjqeEx1COTv_txiy1S2vOa1Web6BGgAL5ShMghFHMtECLaMfuTcX-NLkTq8EXN2MP7D3AUp7QrJI5Ay8MEsN1iYp2VoaKBRSyiZbaTKg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1510, 42, 'https://1.bp.blogspot.com/8B0dKeOlbTRQnhWPXyJRHmGIIRtyOUDrSY9yjgBtJnTB5nyyRTP_ID9rUFIYn1tH8B2Nge9l2E3RWz8W8rigu7ifiFIpdEqHTBjfDwAvo0AM5g8YZYXWVBWnT1v9R1LrW2cAIBsUbA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1511, 42, 'https://1.bp.blogspot.com/CuQDmaRrO6Yu3CkRgpzGJrHDfLwb4n0cy1Y43j5fsWq_zcb7WRKpZz9FDeI9s90rXp_SAiYndxRO_4IlUGtItDO4XeI2suwkEwN-oMwG2lPA95xG8x_wmgr3NGGk1tOg_4yrz3edxA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1512, 42, 'https://1.bp.blogspot.com/xP5OW9PD7PUxK9SXoHJCCoHjsSb-HAg49XWnEw-tS3QVMlZQN-L0yFHcFrZOKf_1MfJaGQNkInVJS_BbIEy_-ySeMbrGJOYpisedjCbmnUf7yqhOFRoxxjbyM3HPqg_HvZOEnoTRbg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1513, 42, 'https://1.bp.blogspot.com/RskIGbUswtWgB3h0m87VqcYmbeEsLb7MDQ48h-mlrX5q__jhxjc4BeBjcnTGOFsF3s6X_Zl9cOxkkJpx0d7EuRp2eALKqeQ_4cG-y5GLqMgAIibYz4azxMPnQGRKQGJ1niVaJV_Lcw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1514, 42, 'https://1.bp.blogspot.com/QGf1rGt3wrQcM_J7se5UXbEzBBcPMuYkpN64NW7EuyIRQUKeqi8Wx8ndZ1C5bHJo2G3LX45b1_oi4pz8n6H3QXrMhA3aTIUiqoLe4pGn4OvsSCed1Y9YnQuxhUpnatq4bfGdNdKA7g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1515, 42, 'https://1.bp.blogspot.com/WbMZlgbfmmuE9WI7t0xIkUeVEO5rmr5zD8MLbmV7PRpEQq6O_1n6Xh51vE7xrg7HUfUvf5jdeX6-zMIyywCdOzxeIiFnpJb2cxenWaDeZSKcWwg1NRi-36-5GDtZvQgwid9-VC53zw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1516, 42, 'https://1.bp.blogspot.com/MoFrqVfuEekvr2dwTeWXpe0Mmgny-Yxi6v3LxIORNAHPGEGK4DNgJwKj60S-2tcgDSZuF_VZHQYyz7Z-2akrNvq7_ZMsFiGU-rE8Eo9Acv_OzTZpXzDNvE4N8ymUcwc6TuW84PgsUg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1517, 42, 'https://1.bp.blogspot.com/uLm3x8E5oR0GHHUb6yam43SCMMC86-hRY26TAeHZzxTvrycsG6WmFckLqrahcGhr4S-j_S1GVWzXH8kWp-ofXQv5ucHXupf6D5Vn8rrITq0HrSCxl3hoyZaP5Rmo5p95urVMXhvvnw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1518, 42, 'https://1.bp.blogspot.com/WviJLPXK-EHFXw6XEhqJoFq8P2ouwBe0EtN-PGbmVlR4-QISdz1qMQVOxV_3vyi30A0yFcVoy3W-xGseHJ_aYSEL1HufC4Wj41UfXKqhS3BYOzKe-ZIlJSe1o8BnzwHtJIsd69fB1Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1519, 42, 'https://1.bp.blogspot.com/t7Qcu29n948TMTUPylVrXUSPDf_1GqG34trN4X5ZeHZ7kWOnEzOi8l9IUAIJolHvw_khER-oKf65dPULcTakZsyrzqeOXaThwtGWNaqnaZnAhIPwmzLoG6jX7_zAz-JGS9e4filaXw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1520, 42, 'https://1.bp.blogspot.com/crmY-1WzSD5MQo5l_aiq7MDOf3BFWYLttuz0uVzAt3dXe3FqfLuY3TFuq35SwnTb3aGtM3QUXH9xLcncU3qCC-JkEVnBVmQOWZZRUrHNutIiSUVeNeuN4aYGjIMXtjiJAhpu9irHHw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1521, 42, 'https://1.bp.blogspot.com/tCUWdbomVKPfV0GHJEYzuW9qdACrZGViPYoIEm4-JLE5413dsF75X_PLj5nb0S5XAYRWL7NtLoQfmKLQD9VqhsLcrOF2m6oJyEq2FyiOy8OiG7VDHojkChvzxz7kFeijKO-Gn-e5yg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1522, 42, 'https://1.bp.blogspot.com/ocWk62mimAQx339H1_VSz60Yl1TiY0IvEMO0emW9goDFHTuBqo_VRtnGjTdt2g8LfmMjmj6kX67fh3-6pC9lVdIIpZIncpNW4YlpEJ1buBAnnK5pocYOSHueQNdUaNJQZq4amghdMA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1523, 42, 'https://1.bp.blogspot.com/8nGWqS6j3Slc1UzLmmfgzRXL5d8O4aI99L7jCxkaIdCQ-wnG2a_Xe1qOXF57GG5kjBPkKwmHt2_g57iNVU1c53bXxLeU7_BO8czdCvMxBgiUuCtp0Hje9b6h5sKkkcvt3erbYApksg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1524, 42, 'https://1.bp.blogspot.com/aLKRYTjK5hDyOD3-1gcQ5pajVY7mi_e_sUfu6-Sv8BgNWJvJB62aws68C_m5OPDpawhwrwkwqjwEaMIe9eKE3zEO0tog4emOIY1Qvnm4K0NRpdwT_6kg_x9HNOoc9BInI3am2JXf1w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1525, 42, 'https://1.bp.blogspot.com/gJDTEFhFVkszEII3IXvc9MISmlaJH0TlH5Gv39TQVmiJhHrQ4-LZsVM6oyOanV1c-nac0vDPOEd9GgSfSnNh3jIBzywRnqGaqGz6CwJPhjmMNBZo_oUzqiLlTNJ9RxRNZPJIKNfNJg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1526, 42, 'https://1.bp.blogspot.com/h9Nd4vwO18UVBeKpPwliDZhOushxkMuQOm6dhNJ7tSZI3IbPfEs6_qHZWrEGmrPGQcaRPM-R0pd9G8tXDeUAcfsvs0Qw58_uHVtfcTfBRsG-K36RFFvH3RoPdKEPJ1iN16yKGaTFoA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1527, 42, 'https://1.bp.blogspot.com/Ko-dgHVD62u9wNicV-OVLWzZLYC9VVSXbNH-djnzP1sUHwK4DIUEH7Tr0R6maKflZpj1zDUfm7utUJ9no7df4qtOxOtcTvIfLCXXijQCQQsGFceUoL_ms06lsI2FVJbSRgSRjQBAlg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1528, 42, 'https://1.bp.blogspot.com/HoaTnWtxtk-AeBZJPMoUSnF9I79viBPfkfDS2TzuSP-0nFx_oq2FX4MqsX1EI7QMthhLBiLp_tGmTHmrwIbHLXyd5MiA5OQES9z7wkGMza98pIJVXDzelnLo41jbVnzYg0dS-60ejg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1529, 42, 'https://1.bp.blogspot.com/rxwwPhsFPYYa2k7dHdqu1EHwYNfkLXKG0jOH9CsnCAxSJu6w0czXGMwtTgsGfYwfyzGelLz_2U-aXacMbsRYsjvuDA3RHnHd6yMkeq6B3G07w31oZ_uJvXWFtUZqo-KasWwzWBpgfA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1530, 42, 'https://1.bp.blogspot.com/epT59u9da8ZB0i4p_nFICEao1tLzOBFLpKkGdyaeFhfUIvbxSIxh4h7PPguWPL_OIems0JSr_f3Mz_JLtw8tHfUGwQgwyDO_NRX9Ai8Ns_mjL5zLBGPJKEoykKG_xMukl7i5J9tYuA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1531, 42, 'https://1.bp.blogspot.com/vIdB9NJS-RhgcJh3DceKmIzGo2EHPnqr1XVG3UicjAapCdZuoXMWHH-jnwF9Sta2KRi9TilP7FWJ5MRq5MgZZBjB9W7A3uiMn15OC_wLgyBEV9HRaSdoN62_ne3tpYhgkgBHTgwpRw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1532, 42, 'https://1.bp.blogspot.com/JuD4kWd_556AQjQ-Mi8AcQ6Fx2k9J4O-HkCAD6SBxWY8u8e_kRwVFb6KL4M2u6EdesgKtHJSRYyygPPFF9G_7zxWhXZ-4wXRcy-8dgTqpp1NWXWWT-oWSETGQ1H43Kh_WrHc4vMV2A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1533, 42, 'https://1.bp.blogspot.com/3_Fb1wiOCJqJ1AJ-BehbffA6RcUgA6q7TqFM4qOPJHHVJPQP3eSwHjWOaNo06IKdO8c6EHWprowhziw19pVyI3nRNd_YoAcmVKq1wApRJeSsKezq5FCAUdgKy1nafPTbwGG0C4Pc2Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1534, 42, 'https://1.bp.blogspot.com/NK9JFPnSpvjr_XW4aqrEgSRNRhDgJhpvi2jCruVyHpXaobH2DOqZ8ENgiUV7B8PwJA7dL7yfvFV0NlSNQHlPVLdywk6SS7d_DZ7xuU9dE1qLGNG06VxnIxCb0EIJ3CycGRb9jYcCRA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1535, 42, 'https://1.bp.blogspot.com/vZCOIe-jZks2vyd31_gN6yZWmKTmVo2Qu2yOkI1wipFL0Sdti-7gL6qHTT5aXn94DndvIbpT51Kg7sSCdLXLyxWjeeI37MrQHeC9u5aXUXq37bL-WOXVj8hdgu827xHTjYyXYw0Icw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1536, 42, 'https://1.bp.blogspot.com/bsUgga-Pobt3Xxm4uuhIjQkyDc1agJn7a56ZCoRc1-Uu_4Ahi3ye8nN67mKbTQEjCT5y3MisKTOINT53FH05Uu1IqR6Gc4B7clkYynbguZnl29-6omB3po-6zCl3_YKTIzg6Y4WGBg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1537, 42, 'https://1.bp.blogspot.com/ahbpRw90cEqkdmXQMRmdLgzSsawk0h7KQPXou49qqAjieTUIs8Ff27Ze4qx63410nqC_26zEOhVsXQ9CJRXk1Q1ZjTHeMye3La2JMJ6kcjTJEqdHVNSYSILgwkdtO5u4TXr0XFHrEA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1538, 42, 'https://1.bp.blogspot.com/VspbIyWJtBwRhL5yzynnmjpN224l5V1Qn0zDfyE4dULoFGLaouBJhHwYoXcIpvzY-JqwCsD087HUD0HJEXxFjY9jpWmNYM5wnO1cK_75AuJ9fOrWJ6goJg4x_MaMrcdn8g-oEoG-Ag=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1539, 42, 'https://1.bp.blogspot.com/nLMwEfS6UdQhht8sPKECXOEvFpnW7d7lW4HVhiqanAIV__5mi73Z80WiJI7x02DxPOTKWL8NvBRZcJgzrStYS1L96o6VkS9L-UONVA-rNKvPWHKG1LoNGsbTL21VQL1hkh4ogXk3zw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1540, 42, 'https://1.bp.blogspot.com/-zm2BmodAOztVKeR9Wqf5YMYQpV-2TBaSc53qdLbe4Iw50O4x8MeTSUziPF2UMNCj5pU0c8XVllk_dUNr9wJZ5DhZS-kfYUTm0H3QHbyZy_zf9q6_vKdDw6bnvIpM2Wpu72dDzdB-w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1541, 42, 'https://1.bp.blogspot.com/kz2ll88kwjGqJAd8D_CNLDL_mKMNEfQ0-s3aqq8LSKdDUXzkQFWOrEmJnpvZAb2A-ttQbFG-j7o016skpHRSWWpCLEiaBFc4JtHJqyylVvdrV0YI3FYGM7Frj7VpmH1YLmpnFS_ZeA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1542, 42, 'https://1.bp.blogspot.com/ael2uT4M9Q3XBTZuXAhEuAnYOOugKLRmI9W5lDD8HVnEcCozJzmbuRXbtRkv4x_pqlhHnrhMiEJma4Gbn_m8HAvkZbnyAuyWSydt-_xlbGHivlRF0i96ZwUxXl6-s_ctHwu260Jg3w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1543, 42, 'https://1.bp.blogspot.com/FQAwJrbyRv6G2FM9SRc7bIbl7Re7yPsTYlQT13xmaeY17ycYdzexagZFleEfU5u6e9vXVUYQ-NMnXPuB7N_3vn9fkwOudW7u3xjwUQzvMYGw4Kn4YTwkn0ql63kIh7a9KGastpKI-w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1544, 42, 'https://1.bp.blogspot.com/QHrKW9-7SPQyZnq-DADvKaMbkEm7a1OnHzv7RTfmSyNq5prry6IZKaxoFJgM2EGgPbaB0UnNLiqWhJhMzLq1IHYVn9iEDQ2VRG7Y4s6WRXq9mdNTyjxrVpQ1WGAdVi9uQTvvpIKSAQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1545, 42, 'https://1.bp.blogspot.com/dwAcUukkOpBjfQDOinSin2S59IRmG0LlqFX_dQYK1NGYQRqaII7U7HGFZGHVRKCrzGfTAOjkiZSiJ5G3KzueTAe7Lr1J-iXNl4BrNQq7PezvlD08aCjFhxT4o5kz1GEgBPs72G69xA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1546, 42, 'https://1.bp.blogspot.com/Q5N0PenkdKJmxOoIg7S_Y-LPyef0BCxK5vJ9_fIVCATYaq5erz4hUeLK0-_IpvuK76IXAYmQ-nBQ8cjUIsJvePoX2F0EB938yScjiYWp3syXDqasZAFWW9BTE-cYU4YUJn5sfvt2bw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1547, 42, 'https://1.bp.blogspot.com/IZ_8GmvIB7f6sCEzqpRwtvb2Q2JgbIajYvKnGXkoxsiHP_Ye9iXo5P-DqZrmfxn16FRVIFDOlD6sek2CVSzvmwphyHNz-sPN6H1RUSg-DWn3gXL_2wldtJHmDdTz6_9V1ohNuAUAEw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1548, 42, 'https://1.bp.blogspot.com/p56P3WbJIfxuCjdwuCYV4tMVHynbTPJbDZG9Tz0f3fSi5CJMiqjuxPTfp6VGbg52a_CMUT6PPRdpGjgiD17S6MHAMoXHlMwXyOnods6rNl4nbZHfYoi_thLPpmS60sLpMWVnyoa5RA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1549, 42, 'https://1.bp.blogspot.com/UY9qefpQpAHYphqNd-Fdu54GKwbhKo1ZFQgd-FcKL3NxaPPhhshtvtbSJyBPuGjgfsauEfv7jR4zXhFSlI_tbI0Fm0kN-MwUKHiaSpfTtxzGecKXY87GOFktLpbIBWe-zPCMQrXdSQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1550, 42, 'https://1.bp.blogspot.com/8vjkB2cP5TzBwv05971FkyC5JYL1WNbzSbaoRJp8uI6bRfwUmLwCUHrVD0PsJXJQN6afRwM8YMKiREmhArs8ywOW1ppGXIvRvC7IcZvZxdeYar9rFdzwdWON0aw9rELRgUXPrZ-zXw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1551, 42, 'https://1.bp.blogspot.com/hC1GXyuHV2lZod7AwR6-6qzO8zqwTUKwpIaxWQtDNPqDLsw3G9tiDC0_jXOGA1HoZJ7XIpg4TCLFFadWeeKhWCHSpE2tQ7twTwWF_SOUgBQP0PVaJ3rFRnRISo3c2v4y8Wu5YK8hrQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1552, 42, 'https://1.bp.blogspot.com/KlwGOncn_QhViBlYQjKGTmawV14jVnHwJG1SidqiqBR1BvcVpwDehIzpMnMHM1j31DLOiJo5C6RZtF3ZRpKN2l07TZzWfeMXr6bBCDeAb3csR9HT2VNZEOXobTvw__FWwdBgE4tnkg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1553, 42, 'https://1.bp.blogspot.com/jnTdyzpEALHO8ufLN5AO67Ne_G8kqMpm-ZhsyZ32Ka_tHEwio2uTh5lCbLv-QbUoIwHiku1D4tE2p9mhml065NG66_bPHyJndpndjMgsDnrSHg01slWO2bBAyEKj2paeAJc-ADOiUQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1554, 42, 'https://1.bp.blogspot.com/RWayyKhkJnxs0kdj077GqbwVkUdpZ1U1vUYR0ykRa_-MBMDUNGh98vLFB8qvMfJWGbOorbsDG3NvENaS4MXFvVEOciN2xBw7-wv-9zPTEkf31EPPZJHfriuZ4WecsAMbw005RUf91g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1555, 42, 'https://1.bp.blogspot.com/ygJ6oQU38mJuNWSRFrjv_KGDHUv6KxS8xRNmn-bocJb3zLFU7km5ebo5QYlLWurrMIVZQ23gHq1cNNjVS2JvwtUlfcyW095pMJNzxFiVjW1NNllpUzApZXlIQUBwFXOGzKuOhlY72g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1556, 42, 'https://1.bp.blogspot.com/8CI9XaPwiC9-6v8yhsV85PVuDhZwBv_tG8q1vHp0b1da9l7tPNyKD4W7selaV8Gr7h141auaMEgyG4HWzGfay74vwg6_ikRm92BvrH2PqX9BGcJ5cGt3yqIh8YVVoJ2aB3RQKrjUqA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1557, 42, 'https://1.bp.blogspot.com/Y855Kom6g-iXj9cqzP0ef3hIjMOl68y8Mf2u244QsL9VKaiJB_vxmskEsndkqNZHUyTt98IF8mfsDdW-MerT77sELDawh6x2tAX32HWl7bcjQlkTHNKaijjiqEO0vTWZB59M3v6m_g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1558, 42, 'https://1.bp.blogspot.com/SoHpUDHJ7HbqUq3RfBc8xwE7zKNKsdJMySTl-0d5p66IgXPYJFk5fd1al6zS-SPoRdeWKs7w85yVSJMjY2iUha6K7YNTBed4rcxh_D_xaYOfJa4phNXmDyyyd7iop39Y6SoCEv0wDQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1559, 42, 'https://1.bp.blogspot.com/78qoKAm6sLN1PvKwN5JTgAaa6KnUy3LqWPvYa-OLUvb0tFtHehLIFylrU18or5twPe8-uoGirp6S63FuzJHotPqsbu0R2otWR5HtlxtT6ebBTJG1vYqHZZwsQj4hlMeRenxwnAcqWA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1560, 42, 'https://1.bp.blogspot.com/1STeqb1DkCNhDMMmydB2ZbKx6UrCQZpgtahlKZbKJyLP_yDJbbgieXh-HH6cF27PD_81KR1q5l5LAmJ6CdZM1MlNdyHtp1iv0TklgtDyCEVCOwoOuREzIoCI9iz8DuH34oTLZkyjKg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1561, 42, 'https://1.bp.blogspot.com/Sf8EXB0MCLng9HqxLyTcUQCQ5hbb5WL_R8W-gowqSmGYfwKYli2S6MBZ6OOg3A2JtTnXlS_FHBIaovzBAKNiylYjTCC_HLxrwHxngNrcubpRvE2EGy3xhSu2pPsTC7YjUy1K7scrtw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1562, 42, 'https://1.bp.blogspot.com/gHrvsP18VYbbOOEKQ4x-klwcfRvMtn3W0ih1nobU-etABDVZK6F0Dk83g2ymENP4V45NGTAS7Lk_rE2XBYbCE_8RLQK7bENhY03PQtpDCtuv1VNF88LTShPV6DSiA2b0B7jBPkFbEA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1563, 42, 'https://1.bp.blogspot.com/CH3DRhl0-lfBChG_3pg0TYsm7Ioqa1JFUMBfmsPHxrMtp7kjW6tKFMfXomim5BApfHIseHn6G_j46mbuKj5rd7yEZWxw9aGyrdbUDsEZ4zKUsTJY7XpKXM6uZb0zQbyCGxYyKjS4Zw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1564, 42, 'https://1.bp.blogspot.com/-NnbEdkyuJxLtEUDqxob2nC2Zig4ZHhs4qgr-ss0a8mwSJJFd3oz8OSDMy0Ypadz6jYLj5S8kK-rhNYYYCK6njPoSAeE5H4R5OD34nzLfDWwJoUugPQIuDvOFbqWN2vS8I8o2R0TIA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1565, 42, 'https://1.bp.blogspot.com/AxHKRBkF3zc9_VC6wZhh0-NF-LAziWyWvir_VmL9TEAfaJRgRPz4EOJbljnJTKy6kx3o8j7_qEAcDWXEkwlkt-i-bxi8THsKvwkZpOU_MUOX67NS5Wx4AAvMfkCrCz2RvdrGAFnKcQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1566, 42, 'https://1.bp.blogspot.com/GvJsGFLW0aFrlwIjDaLp_CygfdakC18LrDYU620Dc997R67vJmZp0bkltU0OTBqdZA2pz6QBsxsZMY6sCZlQbn1ZkgOSb0MwkFXoBpDh5Ln0wfOWKwtTkxQxPUvPhayKU2J0-rfAdQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1567, 42, 'https://1.bp.blogspot.com/7WPywwMWy4KTygRLFGffwo0qXw1gLmG-VFfPLJrz4ELIS_nVu6Vp7DwdC-uXkzutUNhwpe8nO8lQTSwPgD0XKLnS5G-hIjweWDl7wKv9D-DaABRhM7fwpjQv0WPelUKvuu8zfxkFTg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1568, 42, 'https://1.bp.blogspot.com/ViZxAJmat7hfjOI4FBD7fwVoaKwoxodTxnosJAahBpwfri56riKgdpx7Z0esvVxcAAAJlNpcsOiUeocOUou5Iva2CQEn6xDWxTfiuZbSQBqWJEUOkWE1MhE5MFR5m-wgI7gzDtCJlA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1569, 42, 'https://1.bp.blogspot.com/ph8TpftE7e5W5XxDQUQMjU7cuWoDugT8vLLH2uy8rw8nkuG54DoXhgm0azmtxyyqGaj11QdcvZ1n0oc_qJfelAxcTEIGCtKDU2MAcCI0aq6-6w7URyb8MzEePJFugcwIsi-0pVOFqA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1570, 42, 'https://1.bp.blogspot.com/AG-QcJ3tEajd984x1-qezMkRAi0LYiQ_PQBKyJsmRlCFhSA1LdfSoZe1C8_2UzNnBybk710yOggE9lGQEEmNiNyL7KVEPHUTSfrn5L2O3my96su0BN8xCj4kzbxBSVYuApfO1Yhk8w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1571, 42, 'https://1.bp.blogspot.com/MuoZabJei4sxf2WYr1aK4NYC4AB-awANNdRU84n-CYAq8jzWSNH_Jt4raTAYJH8RF2tzflNBcxkCFQTmutCzzpvNVsrUnizwhGTIvcOB0cDdkiDUoWytFrkuamZL4EaxUKQT7MgPBw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1572, 42, 'https://1.bp.blogspot.com/X84D1tf5tLU4zVLcExqA2VlitI2pOWcKGicTVdIbVVEuxaOoePZ7g3caJ3kaQly-ywyzvS_dbJvl0FBYwelGMZJrXiqfsa_59B37gRthpaRWet3ENuJhKe9JhvjOi_l6f3IH-rq_Jg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1573, 42, 'https://1.bp.blogspot.com/1oGVjMA5jNcKcmG_kF09fPoxOZ5Jf1SpzMf5FzyREhrMhtkcdmEhweAs0GW6gkZ2NV4q36XIardYyQJ8p2fovTzm02w0wYdwSUv3d7nKcMi30goDD0YDdGH5qmXn3RhDt33DXuZWkQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1574, 42, 'https://1.bp.blogspot.com/K1-G8kG-uVvpdmSOPPrAfgn-JS2M-1xTp3ItdleYJW5azlmaKS3K4eZ5UuS-gZZoaq49yDrgikHKf4le9ubTuR0qMGfSE_YbPrfGQD2eWCOF__dX_ZsW08k4hKuqBKkyudA7KJVbOg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1575, 42, 'https://1.bp.blogspot.com/qNW7ObabZEWPE8NeH3bGPo-eUzEsPONrsr3UmGFFeRb30njVFe0VPqIsZshZA7YoM0wxJtOrc922e1trFI2g1kSW3HzhR1iq8COxupp4qq6xeUkuV9wDF2g5il3Zkazl3Qamw5cusg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1576, 42, 'https://1.bp.blogspot.com/duAtnToLO1_fyUwzbz6FlWYNzwysPcbzKmfJdYszssvwVtc7HnXRBaVCbO4VfBtSkDCOegIbeqsw5ffZGfTDlUDXvsYjhXjnsfqkpacjIR9eYIMcVnvfxCGNSxrLT0ZIlY2WVgHE-g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1577, 42, 'https://1.bp.blogspot.com/StgB-SwQe-DMnd_AAJ93vZl2---ZavbyAIIbTmyQizGg0lp0LR3NBF9V9BmBLlfGJFuMjDs1BLRw3U4XTZWE8GvDhyRhTfGEgUyGH1-YljByoHk95ZbIbQewz-KzicrcXkkcJy7vXg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1578, 42, 'https://1.bp.blogspot.com/b05KUhc59GfHmcvaJsRVCdzvZBBgWTiI_UqJC7-Tvz0p2IRA8cgKOqB_StHm68v2WQjWICfRhLEInevUCQeOJj5e2lRE0xOYQsaKux1XOXiYvMT9Owqq5dvkkC8o3cFi-HHxYLqhcg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1579, 42, 'https://1.bp.blogspot.com/rvO4SvAq4UKfRH-JwDVYykL2kr2Yg669_e2gFKw8B7PC7Fyz6d13KbGQfa7-REHmKu_WQs9IwKIjAGQkzgjquZafBXglo6dTrkaq4eEU8C178YimyfjVnoVEx9mxtWSomQA2cuiddQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1580, 42, 'https://1.bp.blogspot.com/HJkU23PoTRpVp2rfXwYkrVeuyDr_Pib50AiXQqNdQQjlIGtzgAfJ2l38wlcMkAVi7pbJUSMJuJCVMN2TfCf53UdpD4lOWGg0F2I2SFhIj5jv2RSw5LVSJwqTZ0qaONNEGKmx2LjkHA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1581, 42, 'https://1.bp.blogspot.com/5dcW7wkuZ9zXmgttEu9HQFL8FA5Q2iuXimnkTcUyo8R4Ve1GTlt_5AqXowIZDwwshmYY9IkfuaTrBaxJ97JkVUjKTjf0K1s0qB_LbrJmmDW-Yv_YvpHGKTE6R_2IAHfsiwiqV5yWoQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1582, 42, 'https://1.bp.blogspot.com/8VQfaAdHwEJpAImywbYyClEHmSELmGkbSmBLphX6-Ze6Epo0z4-DcX_kXMqRi3wY9LPeDyNVlzvE8rMxH_EzwirbF0ZQvTlzA03QQ0Igpma0rg5WI0jWqFexb03OOsPbn3DBHE9kJQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1583, 42, 'https://1.bp.blogspot.com/uXN_8vymDXz781S4oaNtGbaegzR6QSQOSssvHqkuN76zgy9WVZ9qKkt0ud5ulsCHJLO47vA-9rJmnx7fpjmIX5Vi4GTt_Rr3bDYek5L5KA8XYyTTsOTQbRFe1rzg2tojIwhS1fYMTA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1584, 42, 'https://1.bp.blogspot.com/QDICltiK0kkrTqwrNBNYU8B9wViaqE8eVCyDIA5GOFZMd4nmtTqIw6NjLX8cI76oydaa3TRODwngyfblgf8dTwrUaVoOHW1FkoHzTk3U-IL1fmYpaWUSoKaQmqBNufukEGcYkrGy6A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1585, 42, 'https://1.bp.blogspot.com/fVNTh2enX9LtgVElfvRki427vpQmKSgFCsiTviisJRnQ2ZxpyTVAEKyvXrunXlttZl9_JrVzoM8jIg762pVomQtVJ8SC0PRIpwf1QI3gScKdJBWFbYq5BbtId6Jr7H0jStwkyymGog=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1586, 42, 'https://1.bp.blogspot.com/Ycn6rZx06Jj_9p0JNbznLbdymdChmgkXFaoIL_Op-qu290Nc7Ol5r2k6IpsOxMd6F6MyV7BZTDmHKLlUqLHUx1OnhXf9a49eWZqnrKawS8T0SD6vgOie7YGBrBlO3ximVHNGFdG6SQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1587, 42, 'https://1.bp.blogspot.com/qyTA3fDli3pzCNpYUqWfEZpCLlbMidLyf5UJOwLbOcXzK-PrT_K5f1aj6a1QsWwrd436_8XAWWnyys6W4DTI2_OMiDdUtmRXlZ0_AtL-d8SQdQB-bxmHYXBn8Yfm-3B-1FcP7guZ6Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1588, 42, 'https://1.bp.blogspot.com/xENMGt7IFxQOzqxf0kZtgMK6wBhWN8Mv4aouFt9924OWEcizZyQZ3aFZTBuGZifoQ7J4_h4kx-iJR3E9s9ytvR1SY0M2gWEx3Fj0aIvXphuTqijJGQs_ubPj7LcYeR0P38NHWiWDwg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1589, 42, 'https://1.bp.blogspot.com/kDCHggFbIi8EhQsAc6Kosqa-9krlpfmDvckEdChUASc4CUmy1F2xeJeuP7d3YkKkaSbMfQ2rmQVqZWR_u5pGNI4UCqen00Qs8atzLHgmYoRj4JjlfF9yTF9zUD9XTK6QHdNgVD0ikg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1590, 42, 'https://1.bp.blogspot.com/vgakA4hs0nR1jXeoMEFBilP8SdQBaqUoV9u4lw2Ah60eGwMGO-mC_p1Il65OH30GNnh3pveFbblLLbEAhRfqUg9hSzmJd6r1fZ7f-W8q0nlxXbuDnp6POuvryV4x_fmPxqDXOosJzw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1591, 42, 'https://1.bp.blogspot.com/QbVU32XAiZ40zWRsp3wbmbbtOscnOl7Ev1jRHZDM6fa1rNBrC_zXxEUgGd4F5h8mjnuGyjOjpl49QTwTrw4sLuPoM2u_FlBbAd9z4WxSh5pIAs5sQAR4oH4LdlCV3DcxaIl-CJcWHA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1592, 42, 'https://1.bp.blogspot.com/7pzVaOJfpdiQATjYrY4LxpvYyntQ2Rsleu-50PY3tThdF69ceKZDH8YepmsS1BxEtkV8fDOktFKp3xeO8Tex8hWwbnGQ6a_04jL6Scd9MK4gTuSqbesxVrHoN4B9U6_NEkXqcok42w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1593, 42, 'https://1.bp.blogspot.com/6DFicSAIDb6i5TPm-yz8lfvCmFBlnKjziXFE1GpaQ_5wj1vvGvY8BjdA3EtF9ouJT1_pfcQiqOkjuSug2logQcT3cnGnU7SutNuzV0V-0ydEEAl1keIrTkMsEpBVk5Iceh1Zz7omxw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1594, 42, 'https://1.bp.blogspot.com/JY7nZieFLzPI-qWlCSkEMmAC41wO7zozhv3bWBdMfnv3X6Nn76jl2ZxBhvLb62QtMdjF_tFzaKEcuvweHC25aM9xWIquI1uccuK8VnRqF8cF3aHfgMqRUmrvTfC-XSgz9swr8QXgzw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1595, 42, 'https://1.bp.blogspot.com/g-Ah_WWnty2Bf02---rqXwBrXEm1Ba8kV97c5QV2cVXTkOT3cI5THlWHfcp4PFmpvcj3vKr87JChMe4in0q1nJnSBPuc-T2DzEOj2xrhZWITiJewlx--mK5-tIJkvgDSX_O0gSN8lA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1596, 42, 'https://1.bp.blogspot.com/ByPX4vUckbFpb6v_ltvVBkCAOTEQGtwKV8ea1POOFyd9-s-DNoopnYGoOpsqjuHYQp4DynfLMhee3Yq-ummTPZxBFc75eCmMVP1eI7t2ODa61ASzNk0ytrvL0YP79WI8_W3chjSnVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1597, 42, 'https://1.bp.blogspot.com/rKVBbu2e5wPBhPDppJ_1VfOYHa861TcAK1ppT2hnJ6O5MGTCymMC3a2GsclUUiEmVfgrYy4cjGv4YcstEzgFr5LxeG0TD97izo_sQgOUjblagzir21uNvjDzFak8PPDemAaQZKzYUQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1598, 42, 'https://1.bp.blogspot.com/KZidhYQ5Ap6RDSOPeZlD54O9JS4Wfw4w_Um6qMSoJM8ld9erH0z4QllOajR0E35q3rSX218QBMUkiEzIpcSnwP3lyXJXcJwaOiA0BGgsJrNe22gwtn-qlbqoh5Eq-u12uffWGjNkCw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1599, 42, 'https://1.bp.blogspot.com/iKfOgrMHpht4tL0Tbjsi4YszZHjx17KiB7QmqVeVjKxR3ib534n5JxiOH8aDwUxxzOWtnNK3st9yeD-QPLsM7aJeE13xzmUefjHiUfTy6Vm_-D8xkG4YYPoADPPJRhkT2--teEzPtA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1600, 42, 'https://1.bp.blogspot.com/MbkkbptXH6R_GfS81e5BYl5ti3wx8v7aTKhPTLyAq9R1ZPZCRXpD_qyzLrhMnsd6nRlceH7YuNv9Bqg42kleLI1IjhB0HTrBXGYgShHosliOh-yRj-Gva6PPwzCfNexom6w0riBxWQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1601, 42, 'https://1.bp.blogspot.com/rduRReCiKwBm3Yf4EAe100CUKjOF4LMfzzAc_LCDKcjaJBbpj1KSLDYXT8SRpR1KQledE8oA_kL_ztytHIxVQ73UpH_wBoiNx2xACFmk9bAfqpHMB7HteJ5Ve_rm4LimCd6tr7cHtg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1602, 42, 'https://1.bp.blogspot.com/m6Y1ibWEKaDLgXQHWaHpsMDWKoboCflopTEIeRX85g66EH9Xa4_DNE4zC_dTFI1i_RZPYTd2LYoo5SFSg9z713HI5TppXPwPQgYcTM314m9ocWUIOeeXcKPaZWkrcVY-CS4QbuOxhw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1603, 42, 'https://1.bp.blogspot.com/_dXvMad2uJ3W_QYwqzdeOc2x4hE-yfeWVi30QQZ5i0KkBXB7Ma5G3fLiL-QvmYv-7750i0tdJ9HN0hMonwL1IHBwUc-ZySIKsAVwWrOxUsbCH7ma-yn4DPkYkFzw4cJxw_ojs9GU3Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1604, 42, 'https://1.bp.blogspot.com/m7Z1Yvhg95r9H3QIiDPpvfU4-Ve4cKwVk2atu51Zc9nccEjDf1r8M46xVEhHzfRHqw4jjS3M-0gppeaPuMeF0PQ6hNe6wA3SNBjbA0tE1uFDH5_PW6X7UP76Z03-e8xs6cduabk3OQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1605, 42, 'https://1.bp.blogspot.com/vwBfmJns0xXt0NeIOJHivUaLwUl8YB6LreLYm2nnTaEL3uUXhQmmn850KM6b4Yu5tJZbfZvs0BITJxXvlFoAonQe5IXJw5ucge1xSi6lL1eFYRye3pVYPo2e6L2rTR3nsGueKanmqg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1606, 42, 'https://1.bp.blogspot.com/T60cmDcovXf8VPKcPhD69j4pWvpLU8GEDRDcyTuYxDL9qG8FLjNUu6VS4vBHe7BaVrFUiIDgv4tfnFL2kGjzrZamX3bCw41cMGXRNbZDsYsO6xMB2cNLH8Wv7e4hHbKaW78SwIkgEg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1607, 42, 'https://1.bp.blogspot.com/HbeAubpkI3yW58-DbazeT2ZCLyKn1SzmJHk1tzJFnycWW838IOFpi3kkIDJxP-jGPDp8GPQeEZhhIVJmUxf3EU-_Oai_7XiXU7JGoM_lODaDa3vMEbfwTEpIX5AuUBbjRUc3L1os3w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1608, 42, 'https://1.bp.blogspot.com/rYjcoRpQpe5qQuzNvESZp1ISg6kWPaVaU9Xec0Oa3ry4Hj6fjc1us8FgGCBSBNtXlZhli7l6Y3H-JYhegHc7gvDniBzyxmHErUmJMGUyzkbFkx2oc3j8M6uwrFXjoGdCLLRfsBG0Pg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1609, 42, 'https://1.bp.blogspot.com/VGxENb0fSjBL4fNEZ-SuhMbuqACDB4E7BouEh5AHYPZkE3peAUdbcyTIRICX4YKFNa2j4gy3zd4Fsz9ndm58PmjOraUfl9rFTorOI5i8K-OtJEKXbWl8zSncBIrFw31JiczqPKQ3wg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1610, 42, 'https://1.bp.blogspot.com/qDnCohz4mfgDZANuuOy6hz0U6uWtbHumWsMqFMinvMu9Z9V6CmyN2PnlGX_bcch9US-fP8j-ETwoOicx7r3FwNiNUqbt5Pa3pU5C0kuBudd64q-COwgxKZPDJXGKSqr9Qazat0cPWg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1611, 42, 'https://1.bp.blogspot.com/bx9u-L8zbjvYgxx-BtpdXV0YiTA1u3oJ9mLqg3Awn02G6sn4P8QaAFJRWMPZ27AsHq6yXiVs5Ky2t8OIbNU2ifd6AMT71-Dol6E-IFlMz-YtvAKIHws_47pZT2chFaIsnDiXj2F0yw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1612, 42, 'https://1.bp.blogspot.com/dqyLwzQfG1f6kA3pWGp3bvxD_ml7H9Kh87lwW8lzdIGxjy_Nd6Hwq5bCIgp71BehAXfShGu7s8zOHLqWlFZG_NdwYQX_gKmQGqNDv1EL5IlYNNVd_dDuUDBfbSrAn-uYnb1oKe9ANg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1613, 42, 'https://1.bp.blogspot.com/UwSXmh6KguiVgJLuyTtycYDb76pHbPcGT8PMVxeeb-YADd3LMmisg_kEeajxbyYhnyz-aOWMAI9ULrY8liNTjG6Kw7WwpPxfTSHW3EfsEFLkBER2_kQwks3N7P28nSjcvfbNPGS7rg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1614, 42, 'https://1.bp.blogspot.com/havevVsxwdsQ9SNdDfRbsAfzzUEqhIFLLPl0LNzudsRaygf-fIO-ea9kCFEwetjWSQRJWrNGdWmmbmDy_UgeukhVEl-9mAnqr4V8aZ60FxrHK-bXFfbNW4r0wWnjQ4MEvbZ2Cd1LYA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1615, 42, 'https://1.bp.blogspot.com/Z2XwpSedPNGAnkfnihTBJq97hix3eyjsWDQli1PdSM_5MVeJotwLw0DmKDa4ZggiEEgAmS6u4FvOlFiDa95SYwYZrKMTkcENd70-LED56PHN7ZSsLv8e1K6EJgdB_iW-9M9nP65joA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1616, 42, 'https://1.bp.blogspot.com/jZB9C0jlbJk_WzFOtuE9Pub-TWqwmbk9CdtRGl07kKy1Hzp8UjgqENI5POFL0wGe9KHvFmYKiopflYMoVB87s-zTDY02nqlg5qikB1sXacqZM2ZJrgxRkpiqC080xa2pP6achKifMA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1617, 42, 'https://1.bp.blogspot.com/YhCf8G4jkpO6AcJiQBKb0VlPJ-VM0l3qcS77iK6c60sIhDnW--wRgLWGqeZvZ17fKjo22n976vvGgTDnNn3Xw5P9-q3SXA0mL8SvydATjknRxbeZBXDr-20iyplT4-ial0OkyoJMeA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1618, 42, 'https://1.bp.blogspot.com/k-XfR1Xz4HH000bA6xKu7xywBzB6EoJJYc75gsegmAmh_0d6cPvi20x6XPX7VCpw5OQaP2fecoMlZimeumjayTCsgVwRyCJMhKfBDmhY-AitU1ile3gRzMyVJYpxVbNGMpvKKxuqzg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1619, 42, 'https://1.bp.blogspot.com/9ZRuWhgVheM1TblQjkDvfwyuLpe3kPmFDHQYAroFc1NCowk-vlEsUD5QKp4Khi40V8oDxgr5YL-aAw5D9Q-vIusGsfv9hApGAuauu0_7k_0iICho93j8OYcl0fKQTRBhY9XQn5x9Zw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1620, 42, 'https://1.bp.blogspot.com/L6X38sCnW1h8tMUXJNCy6E4znxPDxiJixlAXreX98TDtZoUFLmmWxxgB2MHkTGK8q5Wd-4hyIj07WinU8dRtFDUZBiB-TAGwDDAw-ehnQWe7bZBAQ4KZuo2xNi5Kbbg0Rjluxr32kQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1621, 42, 'https://1.bp.blogspot.com/oQN9V9O0Oosk2v11wOTKHrYIplPsoAZDhSFa-dOKPMSeWCltP7yZfjiyt3i5ayLQBdQekCY1B52B6HF6TPzqWf74rhw3ANvDp5iGbhal2HKGy8ozZb9t4l0XL3VpLdVDmT2uQYsqQQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1622, 42, 'https://1.bp.blogspot.com/Tx-oHMf8Tv3GDTGLkju6Wu_HDbZhOPLDHgCLxMqUOPhzhciVszKMQmo7BonC0ZF9J7A_KcblSKMpTeYstE7nLwtXv78uPC_lpNb3aMvc3vEIWeeu601PmGCRzaJlDy6S2EFSqL6bTA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1623, 42, 'https://1.bp.blogspot.com/gEbTslWoCf6bi3DFlkwm1kyh9Fb8388NnMHWi3MMXgpvJ19vImnzx5w4y9xbURPz1WSPOHLIxUzR7Eg5N1FW2QAWcgGbCjErFMCmR-ABhqSajPKUfySE4r7xSK-p_7Wy_x20xaJtyg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1624, 42, 'https://1.bp.blogspot.com/vQ2MgoEESmrP8FCPBLHtrYUyfzq--MOQG0Z8cr66Ivoi5xkIqHRC9Zi8G3nUbKqjlz-s2EGRnU0aXkZH9lRbOgubRXmL9Y8Ar3Zo51mgbM6SXe_ZBt7MH_s8d5y-ds9poF2qMXgjUQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1625, 42, 'https://1.bp.blogspot.com/5j0ULNzn8TIj_ij21OBSy8BayLfbphFPbmgfkUoV7tGaevrplmpqUORITvKLvb097zWzwHrmJX8QjeN3WpMrgtUy1Vx6vZE-UWIVvvtsLGB8NPgCqAH2vLpKWJUUF6ScbIIrkAAISA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1626, 42, 'https://1.bp.blogspot.com/siSvTbv5eBEgf1-z3jFofzLRv8biQMxswwllvvEyvM6h7mqYPsa2mRyYe2rckQmb5bGNfeJERDqiNNNhfT0b5hOprBN17lXD1fbQuXCIkusJZjH4z-vpTg6zeSv58E5xDHywLBjxFQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1627, 42, 'https://1.bp.blogspot.com/Nv-l5n7i7PoZhXfzYaIDwHgLW6E1j8pQqhKXaRy_RgooZWL3cHchCVokCAy2BtQD3YJj_deDmWAZf8Y-Les8iDoebaD1DJ8rur0jQ63ZgjkO4Sdu-4QGxp7wvDkB19rW06Ju4d2_6w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1628, 42, 'https://1.bp.blogspot.com/-8SMs59tqRXTqL-k_X8fHzRPz30puO3h9bHJd0UNfCCEyo-ULEzpUHKJcDnD6eGKhSsJ7-7-JRIPAtaR8XP8jNzO5FCqHWWoxBvYLaT5lYAXofcl0rMXhHX24V7PH69rFHpF83etEA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1629, 42, 'https://1.bp.blogspot.com/BGrHejX9b5slvYdrdsY4PPKP97U34Nj8Y72ufWeMsFzwO7PAgHeOirq6PKi1jBexIP5nU9OTTQ9dyaOiecL6tp1e3vNTnun_L93VZ-xizrQQHGWuvvocYZIwiiIp3zv11eXAm4fy2w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1630, 42, 'https://1.bp.blogspot.com/uTtLqwwFuZdXEWL8kQ5mbN6ehZjJDqmCqzq38bkAMDC1kSDqqf4Ydl8QKjduc6ABrRFUxAOeYshFdarZV8y2Q6WDYbfM2QMDDQNhi8jDI7FrLVHjfo5KhDQ5PbTfXXZd0fB4nhX2Pg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1631, 42, 'https://1.bp.blogspot.com/otVfNbdm5XGfTucuIB96c18tSPZmX8M_17M_turenHiafxKq3ynvWWqEj1ChVGoqbVRMy53iNCVl_OHxHPu2tzujsdthApnFw8NOx5QxEAeQhTrp09r_notrqBj578l198h4eRt8_A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1632, 42, 'https://1.bp.blogspot.com/xbxMJokWkrsbjhUff0KNDEBQmf6202z-S6xByGuRGx9PaLfIU3iOO34O4_rPczFzdSQ6f734yCkvVHsiRRL6uvYddmw-TDuEpwxyFTaRHxVkyGboBs-PYYfRrLZR4yh9x12EntjPSw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1633, 42, 'https://1.bp.blogspot.com/BbMPaC6G0ii6eohX5NLABTyskkLTeQhB0MQDytx5m99MfZ6udVlLgwnK4O9DyN8gECb7Mq86RPNFpllWJ5aGGZArbRxN2EMeYOWHiHA2NFZPqshE0P--03mJTW0HflZWEbSSt9id4g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1634, 42, 'https://1.bp.blogspot.com/06ZqyVKiIpTHYm0afdvLO25rysVCpdBZbKgbH7rAY8lRwh6x0sZMaQkr8o53CEh4inRGCDFYU4o26zthP6EAZXrGl2mlDz4UUZTybWuGUVawbSkALzh56aSBc-_3_4EWNSdu7YJmdw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1635, 42, 'https://1.bp.blogspot.com/SSASGSvHg0Fvi_wZf-cUdhuC1ae771YrLJw-1R8xRHwZ2VaCA3NPd5gNPvOH0AuYJve1Jp05s0L3WBiEmx-iqOJwjwA5FLPRFZvHEMJ68e9MyFnFxjjne-8VjvTEIg_0azNwVrRCGQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1636, 42, 'https://1.bp.blogspot.com/9FJtWdikMUGVa6uvv4Luyf2_NuRmiCDXJaRurK4_OXLexhI5Patq6FgZXJNACql4yJzwrAblmrfSD3ed1IdqDvKLZQOUc_dSkoWXPKjXgsG_nvdZAf8vH55eCzDqM234g3Y1YFoRMg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1637, 42, 'https://1.bp.blogspot.com/YqDh3KCZyFfjHEfHtrQyY2EHdb65YC5ZOig2z9Bzd8LLcr0tmjPkn_VelkQopzaLgIKYaef7PoxYQ2MUmzg6TQbino1ElXBI3WzTpFmDOatmRZH-2dMWZIaOjpO-P8c3T-EBStbPVg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1638, 42, 'https://1.bp.blogspot.com/xcJO-yatwjuPJwuHK3wKOUpJ7NoccD9cI_rsj9One1F_AXi2nYu1N-s1z433yXB0oDL0_VeZpPS93rQ0olYp5jaQ4DBbeGYI2MXkP8dhHjhmfkSMEs2xfqDLYmrMCk21lY4BINGVKQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1639, 42, 'https://1.bp.blogspot.com/155freY3J3BOJiAqK4eSK_QGaHCnlpzpy0ITniJCjxcmWwrUCteRM6WQYQTDUwGI1H7fNAlwDgPuGq8qre-IsGHzN0DpWhi7KUDBChy8ebhB34sb6sFWHHAoEgzyKzsb7yNjXhjsHw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1640, 42, 'https://1.bp.blogspot.com/iw9VZrvEhKGPoo74Dg_05aak_fD5NgqvwY8nWDPdgiSqt57MBSvzTYdF2jAW9sePuFgA6wCGw4eg1mXe5JVTCqJAJFWCZrPjYB4hh9JgrHiB1Hc6QuvjKaGHHuW5KYtO2KtdpKe_7Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1641, 42, 'https://1.bp.blogspot.com/URwWedG88QBhsuICuz4Voyv1CrsJIy_Y2JO_-RQ917CitXA-Wgxsabe0Tm-9j5qMQEck9-Jj4BR1k7OJ6lPNnFZoua6kvdpWn2QUJm0EXB7bGaQ9amocBuInYRKPIhgwVoXehaFChA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1642, 42, 'https://1.bp.blogspot.com/slHyjnQ0l8ykN1L2NQ71v8KlEpiIBWXpd2HHYqpxht1_9RYcESTsi5hCd218ZrdJOpIZhEH1_gowJe8_kQNdRa-eSQItIIADwHSgNUnhwkC2-Zz0ODJ9MHG4txgvI1Mnsv273-vNWA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1643, 42, 'https://1.bp.blogspot.com/vBDiiXh7TG8gd3kXu7MuoucBGeKTPv_btVOZWIvdQ38oKKrAjIzm42ve0ZziJa3pzwo6i5HXGyvxXaWRBwmhjSzGzPUg9YS7kF4Iw9do93MEsuvvRi5rkE0PPAdwKCivmVDivRbeKA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1644, 42, 'https://1.bp.blogspot.com/6HrsniyO7HibhHebfKt9493NN62Ma_qMw_UJ9rlWg_9RvQrS325vMaNJEo02AAlRYYKiYBcH96WAhhnuJoPiGXpvfVu0eDV3ROgHU-TdCSEg2jTJuvtMyxCLU41IK7jQ3BbxqOXdsw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1645, 42, 'https://1.bp.blogspot.com/ohy-zPkdj5Qb-1gAj7U5UchsDI8vgphDygb94jc9SwOipdloErBIjhVwLQlwFaCYY-sAGbk6rFWs0RepObOa_L6XqNs_VXRbzYDO9Ws6YPMwwTwoXA093sCadQJQXHb8tMp3QOTLXg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1646, 42, 'https://1.bp.blogspot.com/ix7CDFhOziAqmQvf_G42IGKBSeqGu4TYzN1Kr_KDdezAbylSbxeF1jsGAyqhzt-hmMUX8Ftf8xcrLAeEhue-YBDNuVB0Hstx7gcaPb1EsPIcb2_ecv542mCnpWei0l40HKsGctaGfQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1647, 42, 'https://1.bp.blogspot.com/2IUm2oGhmCUycsLWDOygUkY6LIycRmpeMIYwzEEmZ1kcqCSC8vDkD_yreQmmANTTqUrzrRdZ3dfA6U1_5CRmy_TVzuyq3hFTPnz5dvNpDmW8MFoSRe0-u3PSN8Odl9nnj5UalQQozA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1648, 42, 'https://1.bp.blogspot.com/2pEpjxT156J6aLzpOVqfT8Npgc6Qfy7Jrib63TagoOL9-3LVb8K8OmgOV7By8C57Lw-f0L8HuMUbZX0pTNKe_7lt7DSKuw9iNFV63vLrqBeFtOgHnWeErMp68tKxbBRYXnPRr8rgQw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1649, 42, 'https://1.bp.blogspot.com/BSMnFuin9xvwhjZX3SgHNGchfIBvEK13NxtKukYvURLmLIMGe2LmffDJg16bvDk2MTt3NhKe8-pWk-_rcYUgOhE5aluO0WQnyLFFbwXtPa505KQpUYZIaQ0xfk1JtmM64qf5qF_rOg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1650, 42, 'https://1.bp.blogspot.com/VEDp__hLmckQxHR4ywiw9PYD0Ds94SSFK1L56abW2mQmt64kTII9lnYcyA4FatGiH9svFyLBQo6yQ86e5PCj-Qhd9pLOqcx06VRicGrpj4F2z0NjNmCVyekJ86W8wOe66CdAorPnqg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1651, 42, 'https://1.bp.blogspot.com/DWkkbiBsPgkDpXc4wS-2rCKRb4kUQLAOoPPK0U7rn83asAymCGzk3KjQ1M78n-x6DllznTW-skmryW1eVYQb-rFWmZNJ4R94qqTM9VHY7hPT_ztgetJGPef4u42Rlk-zx1AYrmakdw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1652, 42, 'https://1.bp.blogspot.com/O0ys-w5kWR41VhnywaGjRQK0jSbwTumE436YCr3usYsqbcly0HeH_NNOK0uVPySLRs-As_X5b5MXR25mLrxqOQ-4yxe2NZFSGXIVToEP8ZSVFcw83nk2nsi9WA1uR5Y-i5iy3X0PIQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1653, 42, 'https://1.bp.blogspot.com/_aL0Q3_HHs7M64Do2iZ-P8rJBxjKpFETXuP-dtVHgszvtV7-t3sHf9DRF_x8wskTRPz77zFA3m-ZoFYkz7Hvh9wk1DoaTe7yigcGhED6BIU8rI_jqQ7-pf3ZP1RXxBFFjRKA7_aAYw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1654, 42, 'https://1.bp.blogspot.com/GIpWVHTNPDvTqYDEIHJ7Bt2JqmLIksso33ZcLE3sXmMOaJSrumjlVvuHR5gP05qxSdoXy2wBzIqhuAXH_TnWS0PvKDY3a4ai9AD5Net2OIUuzGHTxPzxqYxmBztFQxHBHywcEGYIHg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1655, 42, 'https://1.bp.blogspot.com/dVdMIv_N3Mou930UfH1ly2mKqax9-LQenzlPaSfvASePHftadaP0Ashls0L4D8NCmbJzWhbvqs_zVNVccxZANvOOdkDxl8dVm8hPAGnM_gmuD9emINSIcdFCP-jv8z_9mzI3IwBWXA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1656, 42, 'https://1.bp.blogspot.com/MWzcBe2YfhGlI9okM7QThifC88o_yzqvpQFV7DHHNYbTCBccfGbi5NtGauk90I9rhZI4EYz3XfDPPNVxzz2J9GrGW_-UVWT1ug8Ffak6tbdfv7LoZZZYwEamZOiaYB0APO_sJG45xg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1657, 42, 'https://1.bp.blogspot.com/GDjorodjjD6AuICMABv3QNdTEaIZ4eIv4yt0tKJixxRw__dqm1oT3RFVCHQorX_3bXROtfmvkxYmxtpQva2Jm5gpc5PQWEksNlYW4-3QKwdQA_7lGVyWqZQK58DDv41X7UPmsGzoqA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1658, 42, 'https://1.bp.blogspot.com/VoscG0cj4q17wEGIWRBKoBrJRyYiCbF0ZewAvIZYT0PFp6pspAiu8PK81YevzamEbtA4wlNqqCnFxLVUW-DvAL7UKMHe3R9TNvpgC-FswS00EXtCnBy9Pq4T2gKIPO8S9jcoS6_DhQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1659, 42, 'https://1.bp.blogspot.com/XvDyCbEgXEAYGeeVkK54yr3XQJeo40boO0doeiS0R8ZpbYPkum3ui8YZeIWt2ZQ0ZXOxw5dpf-YOxVfyVenU8Jux65-xoidM7dn11nOOJSQcewN5JP1r3djKPm_LfPatFkblB5rk9g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1660, 42, 'https://1.bp.blogspot.com/LtA8oXyHaYonlptLg45UW_MfUEblOHxGQWkt9bhx8dUwhdyqLmm9xBvS_6acxhJ7BlYmLqkDqMS_wOGrefyhgrjr4Us4l-4NHbpXgsf1Bfovo2nRnwjOir1hkWZ0AsCKG76DBK8D1g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1661, 42, 'https://1.bp.blogspot.com/Up8Rfjt3fc1EmZ7iuwr89-b8kxXgGaR1NdQkMQHk9yzVFqPadNQLUlYh_davKi96mnQ1sjLUoyBsHc-iTEiZZMaClpQzW3hEVe258w6K0mBfMS0j-ROtmp89rxCeB8evjOc_A81WxQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1662, 42, 'https://1.bp.blogspot.com/1NxYlNEA2vNFAaNXJ2efB5y-RMydP8rFc1qUK-hwfddjGlZHgrN4kjR_Se_u0RlwyaTpF3X8YJ1GvD8EtJbzRov7SEH1wevaPYIYEea50_0HDII5RpaBkKZq2U1YeIUFfbM2FmFp4A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1663, 42, 'https://1.bp.blogspot.com/tZgiTJAQD_rdowz6zxC7VOcHCcly93PR44wrftLshS_dwKyuogoW0ffW_5WEN7w-dL-JUicIE1Qu2Gv44IpfPKjYHe4i5jH8tGRlop0cccTGJnQT0rLzHO7rXPja9tG6R3usbt_jrQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1664, 42, 'https://1.bp.blogspot.com/zICwRTIJ3GHPTILe5lxgUY3bNMM7BoYJjaQnjLLjrISwyVwv7kg3lcQJJHSsZDipuJxXsyGhnalnChlX0aveIL-2nfQHWE1EHuHjFKVzHYZaI05UI-eWfJWGc6Luo5rd_f1VJpjx3g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1665, 42, 'https://1.bp.blogspot.com/2Zf3AVMChvqBE5mXWGRRN02tuCpQ1TtLCGge_r8IjnbEPh0QCD5ZeI-K6DxHGrtUhlaZMj-PglA_gbEpm7wacyRq8GtpZDa_P9LYGGgM7uWoN5rfYWg8dLRkpFmIZTlGzU8cipAIFA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1666, 42, 'https://1.bp.blogspot.com/RHiQgSAIfzWdyZq63UhgL5-RIWODALEucYEW2rkEpPVV41bQ2-21WTIgD1wBSgIMxShS7Mk3u77shyt7o7MBhFgvWCdE0xDjwsRp4UMtIPL-1AjR7lqehPaU7vm55--emh8_WEoo4w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1667, 42, 'https://1.bp.blogspot.com/hckVY2yZxBjnbqIhwIR7fP75uMXBRZ-dK9a2bM55eE_bB-fS8yALUOU1waaVydWy0WDF7xW3Z5NlllHCUz0fWzAp5ZhJFdtg4X-NT-z3Kps31npbfoZWmiMT3SVVmih5xApszkEgmA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1668, 42, 'https://1.bp.blogspot.com/X8WOEgiDczVeTP4ikPXsw31Y0XUX5oa3AeyVla5XVMCGWKsphjNxvhJjIeqxxpHwmF1mX_-xFM9qy8XIZbDZKNDuz-xat0abQBUCYRB426jyD74Q1ZDn1wdbV2zUdWSBYcfYApgrhg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1669, 42, 'https://1.bp.blogspot.com/Bv-H6PZO2hNCDd_7tcs8qB0tCbud_F9Gh4TtmJ3jVxJW3zJZdlQmIJGudTSMpqCiAS1O-egxTFsGZuaCsTwuLd9UOnX8NVDVWwMwf9mmHXQ7_iDKvLQg-0Nsr9IRY3ekktEBypiddQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1670, 42, 'https://1.bp.blogspot.com/GfeZdFxZ5D44wAby9pHAWcTMBIQSYl5JGu38E3MlKDnEfpzYdQjlDSmgS9dA0cHo3o4yWO7BiZ5vtRQx55Hbk38c1TIjBdzroOE-ousESeDr-j6_a3LSdLm9-Enajycl2LVVp9skzA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1671, 42, 'https://1.bp.blogspot.com/PBV9M4QufDG9vaJq5IqskobhORTCDRu5fBoKjrjh9FWVTFUO-ywzJEWHyqh8bdtsGPZV-hfLRgJyrjQWaB6u0B-e4kcEimGnDOxJeFvWAiL3dt2IHmE_IwkdTygKDMdzz6VF6OxWTQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1672, 42, 'https://1.bp.blogspot.com/xU5O-Je3Tc0XoNwt4fSigS61-B-PbMqBMFMcowY3EOqvSWpBQVQqa7kiK8K84oURUcjMX2JxEIGzfJDNwga7q-cWXh8QjMP8i_N7K0KZGVV-Y5qNc5uSDwKcoI67bNKcaneoa5qU3Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1673, 42, 'https://1.bp.blogspot.com/KeKAo0b4Ud30OLb8Ph9QDdciM8j3lYRmKxLCijZrxBpfQZA6OArFJBayhl9cyE9tvggWc3D9jZcGc70Pg4OqtArUC7ZffHxrt84f1er3XN-K6gQvgtMiO3pRRjmAvdXT5zpuMHrlRA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1674, 42, 'https://1.bp.blogspot.com/-oDdGK-TTtH_q1xGr8oALIyyTCS4hIUeknhJBd56LEHSIUYE5uEagJxe6z3_xt28NqRt_vqM2_86nLWsBI-crmnGBjqizqJoy-4kckDzc1ilB_iATmy29GVAj-X2Mo0E9o3pM0cf3g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1675, 42, 'https://1.bp.blogspot.com/0FQWdeARD82Jsy7yXVz2MspZgwNiRCDOb1rM9ILoKzcEGA42KFBA5bLA7IYMXr_EkO_SKrJqvYWbLqBv3tBb4C4tELRJ7DHMBWJ6AegnheENeMpatSIuZLylCl5qJVF9qrYsPw_GsA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1676, 42, 'https://1.bp.blogspot.com/9gjmq0_qS4bnOz23FOoG4N455CL6YLA4gYq5mvvkv_0ZnFRqOaDa2wFFh16aTwTLxuaV_nALZp9J6LJ-7qCEIK-cyOxxbScT7hG3jGYbrNzYIqnM5tF57O4pDAchNALrqLhRe1WSqg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1677, 42, 'https://1.bp.blogspot.com/PY0FStrnyAi-PgwkgDii_mzQAK8IkbckWF6Hh7nJxqSE_cd0yVdf8hcpLqX0Qd7Wo6NXw6IcYTUW77wpSSn68WjBh2IO4be0n9AyK5Oct0C1uxyhNnOSlF_jheNE4NRBihjwiegjVA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1678, 42, 'https://1.bp.blogspot.com/dyGsFISt5ACv_aCKXoby7yw8MF-NmkZvOqDRmw3qz5NnRVgI4L8Rzm2cAzp0llsec2COtHSzMRkAfmNVQWmZcsdq8E_ZmruDn7gfmln0esOOSV_FEQDRVleQBwpYP1vICsS4hOa-8g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1679, 42, 'https://1.bp.blogspot.com/W5CmgpKzSxweY0EYCN490-Ujr_TK3rRgoYNxXuY136zHsAJRROMQX83Gcpe7wFE4frN1VPZZbPLvPXOP3oaezn1FdDwhi8K7ImOXTDuNrj26Pczl1IeqgD6EQS-STvsAzsElo-ZLlA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1680, 42, 'https://1.bp.blogspot.com/-jf7j_5V9AGWVJUeBEFaJwPYmcvWelAgQr8G16Popp3s33n6QlTSPXLXhxSnYTZYExWkPuxZJyo1RNvaUevH4CHPl2HjbW3Haa5u2VM1b6QgP22mvOiudC0kZUrmDyMRDSmfZfLf4Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1681, 42, 'https://1.bp.blogspot.com/blYBUV14EgI-qmo_sFer_Be1JQLNpso8isvYwdclEdUxrraYxbd66R0crZ7XR9Imk1JZWgRyu2bcTbBl06BtYC2cvW6va4j6OScwKBFp12DMrUgAcNF8BWQ36SnHB_1w4GU6dw9tyw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1682, 42, 'https://1.bp.blogspot.com/a2ZClA7ZR1eQcChhAbYtglBJQZrGSlbAqam0sSyXIZpTuh9J_UUkzU5IYQoxiebI4UmTqgW0r3vzukpXvrAEroAcGqUbEUEf8Vqjeed9f3X-ZybDbhEjpyVfVSshx1552mAa58Ydsg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1683, 42, 'https://1.bp.blogspot.com/x4HUXqb9tIB1mGonluDgTFfVAEjoenKBqnwpInRKK9O52ka4D6uhvogZi6XbYrwEQAXe8jCKWkWKLpBt5dfs2VMcPeqlNpYh453nc4hCfvqbFvQvyHvH49dQ747yTKqdzHgj3kkt9w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1684, 42, 'https://1.bp.blogspot.com/g7W8VkaJiKpWAsJ_gLZrtzYB9_ZNvm1Ur1NbcImEKHAyPCyn86Lva6IxLUoMqOJoL3CADxQRuhOOS0G1jkBB-XxNbD6DHaIrDMIypnN09t_jHj7lpFHx3n-6t0WqilytewgykZ0tcw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1685, 42, 'https://1.bp.blogspot.com/bA0ULRK1TmvWZvPqXZXMsseedjxJ2ppYDzP_or8d5x_8VLI7k1yOmKSGyEpdM4LNEgvlRBE8CujpDjJS63VnZyRrLzy33a_8ttuSj3_NABhAdSqjlHHES1N3A2Iyz8MRbPfTGvzHEg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1686, 42, 'https://1.bp.blogspot.com/MgM4JfPRbv34zSxbcuQ0QfhXZb_4OWodaReOshGAdz1yWUWdhC_Z4mm4o5uA4brks6oE_bXSGZZK-R5yxua2Zv3ozPEAVyuvDvOnWblsp7SXFU_qxGEOmxZVkAq4h8oQDhpyu8aUGQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1687, 42, 'https://1.bp.blogspot.com/OoWpjxI9Kd_0uChiqVKJWY9eelqN5LBxrap1N1H5DTti4Dx4CMDMp20VJg_opWHvVVL-SJAcSU7Zc5tZYRcfbkBLyKm2_VDnaFjZEFry_B1i35ERf_aoE56DJbMIqvd1YF1aFeI-rA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1688, 42, 'https://1.bp.blogspot.com/fFw9fArU3nxYBgR2CbkJ4b7XOGaQMQ3VtN2sV9T51D8Wqt1xIdUcPJQXFlhZr9q_fUN1ZdeQIck4oZPcYD_aneXmjFUzQkEEvVsw_QA2KgPuGpM0bQrfpzDZJtIBAcevkY0X4dnqOA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1689, 42, 'https://1.bp.blogspot.com/TBEaP2KPIN5vogAxcU189ZAvk4fKlcylbKlbC1IRnghf_ags3LLP_zhjJR0JnSmV44rIEVmE9ec6PD4j5iKTOr7aabGhGozxNPtpvFyaS3xBklhsGvZRbdv046ckl2dJlpIkzi1o2Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1690, 42, 'https://1.bp.blogspot.com/nWzKqOOkC4CvfgBgWI6k66qLqQz6rrFEGPbkgkxe79tycus0LZqR6bwju8v7-1lmELS9dZ4guqQHm77W8Y_kx4UnB0Sm-CpBtRgakav2QBwyJmJmFwYBFuk3wdf720rMNzfslquxng=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1691, 42, 'https://1.bp.blogspot.com/7Jk5-JJKxCfWt5-WMsS5rjVB0pBtrZ2Q27JegpfI9gMSrl7ygHkBqEbKGqle_BrpU5RS5WyfPyrfRVwrrcFeGuM5moQCrMrL5RGMAbsTItc80VMHm4n3ND2f-1Mj-38HFdYYy-H0oQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1692, 42, 'https://1.bp.blogspot.com/X3-WzXW30v8KTfdzwAJshfSqPJg0fq_7gjIg_sGobcdGx-iC3AA13evOfZTWkXZOzhwjSWDGmbuD7JMA4_uw5z_jH73WNGyY-9a3tTUuf-8VDO6s45HtPHhur0jPGLKIaOGKGXvf4A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1693, 42, 'https://1.bp.blogspot.com/do2JJv-PbuLkbzFbDiKL8AcdbZWMfql4QJhMRYDxWTaOo56LN9nyekTBRR34mpvVKh9xeuiBumdHOxeGkZMke9tQHyn7NnqQ7Dbzu30Cw2rQ-zW4iZ2YGJZaKVPeWhJkNZ3UyU8_2A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1694, 42, 'https://1.bp.blogspot.com/BtWKuL6WdbCnYd9IZy-IN1a1eAha6RO23tDGgjDzZ7I9oeLCG7fMvQkNp9nkMv2yMTCEodkde7siQS9o2kJMA61CEmwyjJPAcdL0cdiAStiUvzs2ZcctLX72772CWyzyMwRDulje6A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1695, 42, 'https://1.bp.blogspot.com/z_L-_6-Bs1a5U3uHnJFDNy3hlKEaTZ7MnQyR9kpr2fN5jsxOsmTpbSRcgjFve_yxJxoLWTEdo0-4txj2p5mSSU1CqPSONaXcEWBmJbu5jbagbdst-nyxwoLghU84FxbEVtUv9LzN-A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1696, 42, 'https://1.bp.blogspot.com/uG9tfplH7Uix_w8M7L319-KIp-ennrAurywVd6TzTTHlVm4xoS1ZDN_CQivPi19wyJDD_K8dRx3AR7k8bSOdwA4LJa4acsN_Dyc5jjVxeaw-iHSSZHpyVRfk1uJcKWOZ6CMnACwrsA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1697, 42, 'https://1.bp.blogspot.com/V5qCzmord2MFRaNDlF-vq1ReVk_6qwEO5f2NFj2ePPrjcgOtWXAcwyJoftpF9RLEdZD7rbfl9TwqRa3V8-q8N4tPv5jbw7r5fvxW__nDhLQD3XSHAxazFAbwYZ4qf640XWyn1upp7g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1698, 42, 'https://1.bp.blogspot.com/bnZQ2vPK_czpsEUWadEvroYU8YoSWOtNfG-dTwHd4eOuEvPTKHkjUfpfBcO2dmjCu_Gs59HQK4I6CnEFtcJUPA5xx5D--IJ8RUhV4gdLbyvVeAqiMfEuev-KnAtfO2S1EYgiOiLgig=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1699, 42, 'https://1.bp.blogspot.com/KkdIrR9o_aol4XiKTHa8ZUea9U71CKHBadzC7bVO6KTCMNOhO5SIMCtyQj2Meq6f29P3j-2mR8ciKjTdMYjGglYZZmcztp3MncNaiyYg0q0xDUNTiE4RBeBqhubtvpdjWphtzaMmBw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1700, 42, 'https://1.bp.blogspot.com/G20lY0L467_6AF34erp0VJRD37IJaroO2ZX2_ADxNhoQpJzRmNaUNzS5Tm15UdNkCAtdENFnXnzdrl30uX3UO15jOLLsznjBC0SbMfMYRW0O8BeF9sxdigj0RFDIG7-FuWcAYE4a9Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1701, 42, 'https://1.bp.blogspot.com/2WwqR24fD5hsQvQgxEVK8_EOh2JWsUdmAJwUn8z499zNfWqY5BokiH6JalsXRL2auwVWNfayG5bZJmqeb3KABYbi8em0Q4BJB8HqufJ7IIEfaqnRKVURcz7Nk6Dq1KeJlkMPHgT2NQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1702, 42, 'https://1.bp.blogspot.com/akfbDpJyxrQimB16KYTlFmpmWLJLInFCMPoaTud8VqaQRaM9VhS0uEx4Ui7rYj8K59LzI-WN4293ZszAzQ-cQw2TvkG6VB8FC3EPE2dkkAUkm9GXWwlaENy780JNp7FXEbTdO6LQOQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1703, 42, 'https://1.bp.blogspot.com/wp8VIJjMfhcAQSpVyub7o25ulhXO8sr0Nto8qyfyAgQJKTtp3rpNWO0fkgU0R_P8KIP_iKIhmqTzkwlV692gCs3DkSoFZ_chWjsc8BsyylasCAXgPmFfllFsqoLQLiin955bTYJPRw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1704, 42, 'https://1.bp.blogspot.com/xKKyiJOHJ4PeLpXYMBNhCAv7xxnWThEPR2J2bQ8h1ZewaVT16o9WeNs-qCriG4ZejrASudDlB6ob6cpgMtN86Z3Po_d3EfrdetFXE2GjYpOd9CtIlXEg_1P0y_Wx7d5iNhTS5k10ZA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1705, 42, 'https://1.bp.blogspot.com/8hAOQIk_pIS4yn6r9UJE_e6ZJkTaeArN8bDBrOsgLVfC3ESlC9biNDAapAGiCmfXXVHprR8jp9rct5OdA7dahcKtxogP0PxGwA_mdG0tf2vScwvM7FSO-dAmMUSCGRqJ9dYyHFQ_qw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1706, 42, 'https://1.bp.blogspot.com/32eM4fcNyQoe3zKRj0MJF7JKLJeOMyOuP4eRKnjAc5sQAmpspaUMuncPNu1pzXF1NvqSbikIQhfwZzc_tIODKXbJs_g8WKN7upy9l64PUKYqVspm1bAkTa_ZNi4qsL2MtZM5WubEsQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1707, 42, 'https://1.bp.blogspot.com/09xhypQ7C6L_fjBsQJQu2ir8cI_mOWkO0Zc8EKC7yeOyN6j2IYX1D1INIVmJbx4nfgUt_qvid5gV2IFFPqLg7VQqBf0xc12kL7Qghhlh40OTRTeYyKv_ExGG9YZh8KEgMZ9-W0Cc8Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1708, 42, 'https://1.bp.blogspot.com/d4vZul1nrsYY5herPXe_BljxwfjUAmYT2NgIIGckUVZr-HBo9FQwrXcv4qq4BJzmJvfRCqXySp6ClRWVgyTvfyTfuWL8KRVq4X8E8RHwXFXP7ZZvB8UkKMHSwelBJAEOabWgKTN8aw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1709, 42, 'https://1.bp.blogspot.com/BbXjQha81Fk6vbU2AyyFoedyFbGaUwGOVqZf2FTeyZJiQqLrsNOO7skaDgrc6ULU_MM3ZzDOwkxusN_TCLZTkPJXitOVagQHNkhkOHg-sxXgQYwG9wNmE58KzGWJaHPN_LElc24YTA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1710, 42, 'https://1.bp.blogspot.com/kogdDBZNZoCFBzEjZOmIZ1JsRzEizhMRZTd7kXEc379JIwAVYsE4qPJ407U8XfZQmBdkPcHhjVlnvWWDQP67l6DjLNA7HYw-fjksNqWM_SJPQPTqpN2QFTlpd4TkiKtj9jHZiJX8yw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1711, 42, 'https://1.bp.blogspot.com/7L13Dfb1cKiRwNnafJq5MALlyf-aA2mAymxG2SJUNB-l416kO8_0q_h9zlEwGjxF9TAhF94R3lxNTAyR4vwEffXF1hI0RRZ_17RNzwlyYaQzTDWRArtnkwDrhK7aAn6nvCjf8PWW_Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1712, 42, 'https://1.bp.blogspot.com/AyLmsoClpvtrVE7AZ3SvjeanRKwn8W6S9wkTDXZdWAqNe_c1ZmTY-IRtW4QThe1efK3etm6g8uqiJogU45kylQSrGWZbqwVc3rgwiPua3hCSXq67gyiXikdz9vyu0h0JQuliVmcKGg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1713, 42, 'https://1.bp.blogspot.com/p30KkGWiwmgDvcaJ_vz7kVMXZkuMmc1XkQmYWQ_6ZBpTGt-VlQt3Sm7CmojsBEi1OGdesNYQr99k4AjFwT8sKUQ2zYZlheWbQ9NRMcJ5Jzr3lFZGFo3TEe4_owVrFUwyeDHXq7JQkw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1714, 42, 'https://1.bp.blogspot.com/EGEFK_TYuaRssBol3SX9NEvsPIS8sX44rZePxN4VcVsNLPZADEx34xP3GcLNwHCUTXRYTNQsCNhUz0NaqNubAmt3xjaRTZnU5s_jqTRVAJR41q2PJNjoeodW7MxxCvUm2Sez1enJFg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1715, 42, 'https://1.bp.blogspot.com/C_PJ8JF8ZKcvIcfxlZhMQ5yIPI8dKSqPItYbUERzl9oENJRu9MlON1b4FzR4VS9y1xvibeFQLvoBdvQ24Cl0IGudCmncDSVm7PCxNyQIzKWg7krNyIajLJvQlAvjKLDu5SUw3DkGng=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1716, 42, 'https://1.bp.blogspot.com/Eb_ZrG1Y21JtsH3E23Qri6NYx03U8ACbXH6zJPQVSYrDHs3Wf1rzv9LJx_6Ipu6dkK7pfWmbC0lvbTkUCZV3BBAo0khuR7tZDoVyqqnj8xLWIpePVcckJPZr0T7Pr30JNLlJOqMVxA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1717, 42, 'https://1.bp.blogspot.com/6w_tB3On9IwS5FQ87oTfyygo25WSzqh2bTHZPi9tkZzhWrqBxIQRxIbkracBGd6WTLFiDgzYenPtpMt0B9aav1iR6NKDUayFNahDzdn0hwYsJz7W0-Dp9iFHMdrC8gmA6N_KPPLBgg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1718, 42, 'https://1.bp.blogspot.com/50yaKb7dbEzFucU66R2roxL0dHSK6qA0lIvCPeRgXwYgBzRTJ-LnrSxOwLCXCWW4Bh3EOWPkca9ZAIlaKwQBXlHRONeVXOMXN3Q8JBBm1NOBVGnwlbTa86uoPP7v7PVKWfAG58cpSw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1719, 43, 'https://drive.google.com/uc?id=18xRBqlOpfGWW-3jdlrqpftXx-mu68utx&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1720, 43, 'https://drive.google.com/uc?id=18wq-eyK_tliRCA4sIaKkBD6oTwYGGVrp&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1721, 43, 'https://drive.google.com/uc?id=18tN0fI2f80PMEOmubujlQLWeb2r3xs3t&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1722, 43, 'https://drive.google.com/uc?id=18slovwlq_C9sQMiScwUA5BsMezwjggHw&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1723, 43, 'https://drive.google.com/uc?id=18sbLROCfYiuUEPB2so9z-w7oCCzZr8q_&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1724, 43, 'https://drive.google.com/uc?id=18sFjqq4y0xXU4YLqk_lPaZbxPiCG17rs&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1725, 43, 'https://drive.google.com/uc?id=18qKFqRc7yJlsRgNuKQxL37Ro4xciahEU&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1726, 43, 'https://drive.google.com/uc?id=18q05pFJKtSlPsuuDv8XRbN3w_LFLuE5v&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1727, 43, 'https://drive.google.com/uc?id=18pSq563O1xJDPNpd8kTlU22HG4YL6KTU&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1728, 43, 'https://drive.google.com/uc?id=18nR5Cs-5IG48rFo0M9VK2P2nfvHiUrJe&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1729, 43, 'https://drive.google.com/uc?id=18n9j1_AtvMUj7LIv3w6e3AEgOCI7G3SZ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1730, 43, 'https://drive.google.com/uc?id=18kiGJpexo0zI3hrONhSCTmZArgCph-Sj&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1731, 43, 'https://drive.google.com/uc?id=18kYiwcvF6LYynriJ2yMmsb77ZKlYEm2b&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1732, 43, 'https://drive.google.com/uc?id=18jVjBETFZgRz42j99PraiBe07m9PnadU&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1733, 43, 'https://drive.google.com/uc?id=18jDPWFsSncZKEwbJQk7nF7RZIUl3_v8S&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1734, 43, 'https://drive.google.com/uc?id=196wvTTPL6ejLBupcxA1qlvDPTHizPXOv&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1735, 43, 'https://drive.google.com/uc?id=196-ZrdoOzKzOt4WNeqef8KcRD_IOEpFe&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1736, 43, 'https://drive.google.com/uc?id=195g_hHFV7HwUoK3M2syesK5TWumNOzIg&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1737, 43, 'https://drive.google.com/uc?id=195Z-KTBnUJxeZY1CefewwtSHFEkGu8KO&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1738, 43, 'https://drive.google.com/uc?id=1953U5sDtNmVuQILXgxPEe3RrgEdryyAE&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1739, 43, 'https://drive.google.com/uc?id=194IHlXpobSsNN2iFwdrzKMfX786SNlnx&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1740, 43, 'https://drive.google.com/uc?id=193OdmE4Tzw0McOtAzjvLNORxyrGDg-75&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1741, 43, 'https://drive.google.com/uc?id=1924j4nmdq-7vM0njsFhYkLiTqRsZfJPv&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1742, 43, 'https://drive.google.com/uc?id=190RCn48C0BorTim280BeZgp3zteXQmhM&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1743, 43, 'https://drive.google.com/uc?id=19-avmMovJu-qu__-LyWH9qskx1KKiYJm&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1744, 43, 'https://drive.google.com/uc?id=18zjVLt7vae8bGiu4LW4_JSkoJMq14d8a&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1745, 43, 'https://drive.google.com/uc?id=18ywCSMUiyfBIjqqakv05pmnDGVr9TYLp&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1746, 43, 'https://drive.google.com/uc?id=18yan8kWqadYBgyV_wOIyBMtf1fOl1WxL&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1747, 43, 'https://drive.google.com/uc?id=18yOWLukNRL8hhu8okDCZBt3lm0G9Pvyy&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1748, 43, 'https://drive.google.com/uc?id=18xdFsF0cEYhWzedx0k8n8RtHPTht84DQ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1749, 43, 'https://drive.google.com/uc?id=19GNyjosquJP3ac2RJDGbEN_yHYflLm3F&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1750, 43, 'https://drive.google.com/uc?id=19GGoBHVBkuj8jcrY_o18-h0BMAAegVbI&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1751, 43, 'https://drive.google.com/uc?id=19G72ExYeG7CYId9Z47sNEDVopEn-aLIv&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1752, 43, 'https://drive.google.com/uc?id=19FytyG6Y6TL8d-4lNwUZP31AJHU5u7UV&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1753, 43, 'https://drive.google.com/uc?id=19F9FoeIEKZpq4kTGDpFI33M-oNMhFpoI&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1754, 43, 'https://drive.google.com/uc?id=19F131eTCMEzV_CPHCovtzryE5AWmqoJp&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1755, 43, 'https://drive.google.com/uc?id=19EVe0S6VcCsPwXCK2KvVdilS5QX3xVsZ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1756, 43, 'https://drive.google.com/uc?id=19ETY5Qlq5Qbtd1VfJw7KucCrs1_WV5Sb&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1757, 43, 'https://drive.google.com/uc?id=19EO5oGnsOQ3ffSmcqjirbsnPyoGhD_Zj&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1758, 43, 'https://drive.google.com/uc?id=19CeWD5Nh7-cFSuiAC0rsMmu4V6qVaEs_&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1759, 43, 'https://drive.google.com/uc?id=19BXbSAe3gFsLbkWpWqpCxCpIzs8QbqF6&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1760, 43, 'https://drive.google.com/uc?id=19BCfVvq4SD18RMywaoyYeZDR9D3KaMsZ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1761, 43, 'https://drive.google.com/uc?id=19Aew37W9mavf1hMHgVpuHs2WGzGPwfN3&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1762, 44, 'https://1.bp.blogspot.com/C-aHvbffm8rrEhfDaTX2ss7uSSHZ3enAZrSSPV8AseY8Tio_MYF2Xjc92QdmpXesziWIbr6weL-osKWwUfJxno6c9_izRBb8yiEPyhRxYpB8qWcMNGi6pwUm0RSi5DSOS1-c7_SDIA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1763, 44, 'https://1.bp.blogspot.com/E6-k86E_F5nr-TbGdiUHDvo_8bKfT7BnyX5sn9fyK-1L0xPpCAi3uC4hVZNBSm6M8b7-S7qO6hFqpycQGfOHR6PsdGDFeyk6DHFT4zckSB0yWdinQ4gm_83XdQTgUl4bloCPWCVfdQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1764, 44, 'https://1.bp.blogspot.com/l55mC3jcdG4UO7SlLh0ZKzKauKVuc-1PsvYFBpR7HIkyg3vfosm8lMTjnliZoP7rptktDI8TNqpnQyRU0P1bPeEhnjXwEnAJF_-OuPJkpckWI6LIDnZQcRFU2Hg6mquE5kpovge6uA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1765, 44, 'https://1.bp.blogspot.com/HMo2skyMhVilkofDbNvexOgQXHG-NsMKxHCWW8DtCUFIQzPZNPxwlODvjSapAYPiaXwc39CF_ZyDQoJbxdxZ_wvwFT_N8H6CUcgPPuCRdAxwGTzImAlJQyA76aliGkwnLYzIW-dbXw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1766, 44, 'https://1.bp.blogspot.com/KI5uTV8nuwKD8pNXcbkJV0YAKvd-QWl8KxyyPuDuUIiOKFLh4J3kYhklmAc6qdGf31IOqiCaqcCEGVVYX5OdJ3i1aIJGlo5c90RIID-SVH5PDK4rS5YIBrjS-0ZWzId1h2VI9oi8Fw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1767, 44, 'https://1.bp.blogspot.com/jh-wZW5Knze0MH_lbMLqk_hfy5B1wmrPEAw7nTHg8y1qg9ayajEEjG10SaPhTjeoj3GyEYLA-8jrlnxR11JoQkTyM62OvwoyWJXudJ3Mb1p7-Hy3JLWpGv110ikouVJBokQr_xv-lA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1768, 44, 'https://1.bp.blogspot.com/MsKgcLmggf9YCJYjhc9RLvxIv17bzTTmlu0EN2KBvBdjJczOgHo9BIBV2XB0_dy8iZdMT1wYTfDNf6rGYiugjH1N3KXevIN_F2wTvL0w6xeXsbtpgiR5RoH3RZ5viJEqdL3ZUPRQjA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1769, 44, 'https://1.bp.blogspot.com/uNX-fU4Li-XE2Ih5E5x-WOkY4fuwBHoan-Ntx8MbwbMBmCcaJpVWKxw4UH7ttW7p0Wqlmp3UhWcBea7IY9E1sMKICTE1JQDz8L_x-IpOTsvnCZ39cnzRa1UfcJc5oMIG5BvoWLPDQw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1770, 44, 'https://1.bp.blogspot.com/0FxLUj6OTVEDc1tOGo25nkK2RGnmB3tzqAWEDFPEYxh79U2kyhbD_T6fL8O7RfOkTKrzWP8e40k4OCqNPMyJL5ghFxUPD5hll38V0DW7tjqR073ChntyULsjUYXX624UBYTmPHNA4w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1771, 45, 'https://drive.google.com/uc?id=19AGAewBckMZBegf7wCUSoRgCdDGs-Rf-&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1772, 45, 'https://drive.google.com/uc?id=199XSjDD3VgfQfFRQyu0AjzL1ZkP6LYg6&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1773, 45, 'https://drive.google.com/uc?id=19RPiWk-dbG1aKC96hK0sRBAT-pOV-whS&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1774, 45, 'https://drive.google.com/uc?id=19ROQm3I8CVrnD7TTnAn6p_kh3L8o4EQO&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1775, 45, 'https://drive.google.com/uc?id=19Q3YymdN-Z1moPkeyFFTWo0vlxVyyZtN&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1776, 45, 'https://drive.google.com/uc?id=19PQ8ygDP00HDcUFnnAd7kYtA7iszihv1&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1777, 45, 'https://drive.google.com/uc?id=19Nu2hhyHDb7nkXNoIDJGjCbckgNXQWbj&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1778, 45, 'https://drive.google.com/uc?id=19NSLuO4Mc1ABJohInrEcc_egVqRIkFdH&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1779, 45, 'https://drive.google.com/uc?id=19MfKvJWFal8Ol8dx1SNvEQ3LlYXjbPnF&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1780, 45, 'https://drive.google.com/uc?id=19M4P-7XlttKALbP995F0Wj4fl5E-awYn&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1781, 45, 'https://drive.google.com/uc?id=19LrMF6xEr1cjPupNaenOom2NiH2Nib55&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1782, 45, 'https://drive.google.com/uc?id=19LpK1VRdNqUEuCp6zmR8YnE29uBxvDxj&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1783, 45, 'https://drive.google.com/uc?id=19Leoeg0JG333zScuBKwOzXM8U7y8Glka&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1784, 45, 'https://drive.google.com/uc?id=19JuDyO1lZylThLTqkOuuIJ8QQecsNSxh&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1785, 45, 'https://drive.google.com/uc?id=19Jos38LROCSPjPUNBzDPEDjm0skbdIUs&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1786, 45, 'https://drive.google.com/uc?id=19IfMj3jgx_-28dGsVJYlfnS8nyw5IT2t&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1787, 45, 'https://drive.google.com/uc?id=19HZKl9f_A4r6Aub0RfNB01Zm-5YrfqKI&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1788, 45, 'https://drive.google.com/uc?id=19_9l-zgxiDiwitfllqpBHw42OFLfGdOh&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1789, 45, 'https://drive.google.com/uc?id=19Zv15A1TX1Hyts9GrAuwwZDHL7ffe4k6&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1790, 45, 'https://drive.google.com/uc?id=19Zd27MS0tqfHLOH_RypCgaW7N_-xDzgx&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1791, 45, 'https://drive.google.com/uc?id=19ZTUcXw8DjRgXZhMwdbgP9bYDZZ29G1t&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1792, 45, 'https://drive.google.com/uc?id=19Wue1OtiODkA2Tx3y6fHq89ODw3yuCxT&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1793, 45, 'https://drive.google.com/uc?id=19WJ0nXjEFSw0wmip5pnWnKYEEijA1Atk&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1794, 45, 'https://drive.google.com/uc?id=19VpG9_8NbrcplO4f8tLCcwwYk3Ik8iP3&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1795, 45, 'https://drive.google.com/uc?id=19VipIfR1e_I1ohfEo5tLsGxtFMT0M_mC&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1796, 45, 'https://drive.google.com/uc?id=19Va8p2S_2RJt-Rt17Ib1-r3aqyG6umVk&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1797, 45, 'https://drive.google.com/uc?id=19V51qoM2PutfiZctGWKmaYpd35baaRUv&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1798, 45, 'https://drive.google.com/uc?id=19Ua5Q_r2aKJNuBVgkwzLf3MViks-9bH0&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1799, 45, 'https://drive.google.com/uc?id=19UU8_f58Xkojw_u9Xe-F7Y3ZQC_X0wVm&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1800, 45, 'https://drive.google.com/uc?id=19UA_UzqaQOOw92v5tmykcss56A4rUV1a&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1801, 45, 'https://drive.google.com/uc?id=19Sh-UQJSYlm53rX3mRX8081yPtpMgC47&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1802, 45, 'https://drive.google.com/uc?id=19RTwxZEB9KIZ0DRCr7DTMNjzGkCn0gwr&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1803, 45, 'https://drive.google.com/uc?id=19iuHkxKTgBsuPPqxvnN11icLLGnWgzFg&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1804, 45, 'https://drive.google.com/uc?id=19iVWy8bft1dwEnr3VO8ENpKTkbzTSL-8&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1805, 45, 'https://drive.google.com/uc?id=19ht0cZHqJke_vJ0AiraGeufQZojbK7en&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1806, 45, 'https://drive.google.com/uc?id=19hpEj6mLhVfz7Dbks3OMFccWupLv3z0t&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1807, 45, 'https://drive.google.com/uc?id=19hXBgub-SyIBKBH_bdaORu86uamrqr0Z&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1808, 45, 'https://drive.google.com/uc?id=19hEkHczhSSCDub7nnbyltiIjCT2-H-yg&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1809, 45, 'https://drive.google.com/uc?id=19gzFSlLfK9oADpbMdQeYhzSfOgS-87y_&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1810, 45, 'https://drive.google.com/uc?id=19gQjlQtj_1CKBXqA1fH6pshrr6O4I1jj&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1811, 45, 'https://drive.google.com/uc?id=19ftHxq90yBub0iXCJMRwOVLg4pW6uU0S&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1812, 45, 'https://drive.google.com/uc?id=19fhxCZVCtKmYmK2iYc0GsUL4juWAr-2_&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1813, 45, 'https://drive.google.com/uc?id=19egJh9SzRofzBaiVJ0AKXpb92Mq4bcEm&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1814, 45, 'https://drive.google.com/uc?id=19dEbn01b36evtIcN2STjEhkDLepQ-pPq&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1815, 45, 'https://drive.google.com/uc?id=19bgPSS8g6PDMUPZ5PgTpqxrL7JVKj446&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1816, 45, 'https://drive.google.com/uc?id=19a8sz3UwIuTwz4P9wYiBPSvnX5nABgCM&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1817, 45, 'https://drive.google.com/uc?id=19_ZJLflvJjtTaakt6hnCuIx7DGpXZLRI&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1818, 45, 'https://drive.google.com/uc?id=19w1mc2KL9hf0tKG8uBKQtWqzszj69JJ1&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1819, 45, 'https://drive.google.com/uc?id=19vfs50u5QQ3K_fkJW_WOofoP22KYSGv2&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1820, 45, 'https://drive.google.com/uc?id=19v4g_fO5oOr4C0zk8s4Ldy7mqRMNkG1H&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1821, 45, 'https://drive.google.com/uc?id=19uu4up8PNvLWHyOJNI1ertxaL_ydPxV1&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1822, 45, 'https://drive.google.com/uc?id=19urbrBOOgatnOmHXd1ds57kKg96_VHLo&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1823, 46, 'https://drive.google.com/uc?id=1TAv9Mm4TlhMnjERFf4sdkyLH6hppFvfE&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1824, 46, 'https://drive.google.com/uc?id=1T631RQa4Qukpy_QGeEaRjfhlkGMMcvbT&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1825, 46, 'https://drive.google.com/uc?id=1T5X_ROIwPbDYMwCIzS_xh8vbu5iGOFqw&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1826, 46, 'https://drive.google.com/uc?id=1UahF1tJk5HtQf81_inQVlG0mhtTGOnkJ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1827, 46, 'https://drive.google.com/uc?id=1UWWF54uzVNM0TvDQyydNQ7H5YKgMP0H6&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1828, 46, 'https://drive.google.com/uc?id=1UUCS5XMfhyNuaQVor1E4aSTOj06fHdbk&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1829, 46, 'https://drive.google.com/uc?id=1UTJw_WVZvQm_mUw5AyFpqiorvWt1psKy&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1830, 46, 'https://drive.google.com/uc?id=1UR8HbZxWMz6LCiJEj4fKK45Or8zjKnc-&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1831, 46, 'https://drive.google.com/uc?id=1UQp5ZFiKz6_t03bG7q5e9tVPcApNguTE&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1832, 46, 'https://drive.google.com/uc?id=1UNOmccDnGWDPWzzVVPSuwtAaJs8D7oI_&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1833, 46, 'https://drive.google.com/uc?id=1UMRJ7Q2vmuX-K9muiWkNeWpyvfEKKR47&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1834, 46, 'https://drive.google.com/uc?id=1UBlf7t12jDjhGmbyXmCFPPwfP8_zi1mN&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1835, 46, 'https://drive.google.com/uc?id=1U7fMS7MZS3zTlwojoBm-5R5IO7nnRt4e&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1836, 46, 'https://drive.google.com/uc?id=1U3fjFe6QD6jTrLePB5Ho-wdBccLetn31&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1837, 46, 'https://drive.google.com/uc?id=1U0OiDbveZBnWqIVXwGUBQkcF8ohUOsiV&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1838, 46, 'https://drive.google.com/uc?id=1TuqmlNUFtyDYNo_q3X4rOmPn46jEVfrq&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1839, 46, 'https://drive.google.com/uc?id=1Tu0Fnu5DU6O0BV8g0c82OTpjPc4DALwv&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1840, 46, 'https://drive.google.com/uc?id=1TtBO0NqsOK-lAdX7SjNlWpPOr9Swg7QJ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1841, 46, 'https://drive.google.com/uc?id=1VJZBQFFVHkoLijDAEMzN61scV03CLkLL&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1842, 46, 'https://drive.google.com/uc?id=1VI4ma_oYnEKy3xQT6RtDOLNjU2Iv5Obp&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1843, 46, 'https://drive.google.com/uc?id=1VE7cVqugXAkQmqf7SX8h6PXhG2s8bb70&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1844, 46, 'https://drive.google.com/uc?id=1VBaKUcJNbyEW7uO1wz3Xpbv_UZak0raJ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1845, 47, 'https://drive.google.com/uc?id=1VA-yB-a2J89aImZHMzAY0QYifTRCl442&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1846, 47, 'https://drive.google.com/uc?id=1V8Go2lXzggBn5sPdYVvvJYSpflSq5GhR&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1847, 47, 'https://drive.google.com/uc?id=1V0ZdSp5SQNgJEqoLH-LwF84dS31V2D_p&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1848, 47, 'https://drive.google.com/uc?id=1UzzoofQbcAth09fSCSOpGJndaNgUIOff&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1849, 47, 'https://drive.google.com/uc?id=1UxdR4zOGg0L_FWObppUhx1nZOOXzDRxJ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1850, 47, 'https://drive.google.com/uc?id=1Uvi_ylsWJqcyz2Uv_iJkLmwgwy9jOBkb&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1851, 47, 'https://drive.google.com/uc?id=1Ut8y0ZWYtAX4eXteikW7Mm0EoiKfrYWE&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1852, 47, 'https://drive.google.com/uc?id=1UsoaU5qMU7qmdDIIq9qHkxViY7gJ2yza&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1853, 47, 'https://drive.google.com/uc?id=1UgiKfqk3VJKKXUMp-8sjL5kAoS7QJgea&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1854, 47, 'https://drive.google.com/uc?id=1UcbVHCvldOH6BWqrksha7qX4RYwgtcAJ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1855, 47, 'https://drive.google.com/uc?id=1UarMh7Gxrunm618Kk6Hq5BqU9T0hp9Pu&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1856, 47, 'https://drive.google.com/uc?id=1Vx5RhLCeaiQBJIA_Cerzne2C_rARHw3M&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1857, 47, 'https://drive.google.com/uc?id=1Vwk0Fedxw43QehWmr4Mo9SwpBrrO4Epy&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1858, 47, 'https://drive.google.com/uc?id=1VmzWP3Tf3kCqnwYhKa4aFXllUesiXsSy&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1859, 47, 'https://drive.google.com/uc?id=1VmdJ6r80dpSziikY34Hh3QUXUv3FcDcR&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1860, 47, 'https://drive.google.com/uc?id=1Vm5BJnaWYwDqtant8nd4Tsj4SSTxSYrI&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1861, 47, 'https://drive.google.com/uc?id=1VfjkBeSXb18MzTMnJ4j-j6OQOIg6F--b&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1862, 47, 'https://drive.google.com/uc?id=1Venfp0GgPcod1fam_02JRqTklLz6VPb1&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1863, 47, 'https://drive.google.com/uc?id=1VeHBgjOAX-D1SvT73vwKV2UTeQ05sNDo&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1864, 47, 'https://drive.google.com/uc?id=1Vd1V28Df-aJSj3RXkEdAMzRZlYTJFVQb&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1865, 47, 'https://drive.google.com/uc?id=1VaxqKt9UB8aMhZ05mn5ZbUt0Dt4q2k0a&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1866, 47, 'https://drive.google.com/uc?id=1VaRwXnhyhDtVYbJBje3lgkiH97v7Qwey&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1867, 47, 'https://drive.google.com/uc?id=1VXYthpX1sjZw3tcQnwv3UBtVP2-sQLS7&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1868, 48, 'https://drive.google.com/uc?id=1VUTUxugpHbl7RW9tbsc6UJDdkf86F4yH&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1869, 48, 'https://drive.google.com/uc?id=1VQd2po9rTFNsLodIjyll6z9l--hy9Tu9&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1870, 48, 'https://drive.google.com/uc?id=1VOYuWwImJynBjGgxtpouHzMXGAeHK7yN&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1871, 48, 'https://drive.google.com/uc?id=1WyGtHvHBuvcjItQc6mA16KX98tKBo_kL&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1872, 48, 'https://drive.google.com/uc?id=1Wy9nochObUO732sMKaH_e9_TbYN58KMP&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1873, 48, 'https://drive.google.com/uc?id=1Wk2Y4BSBCaDCzV0ibDwWZoxyzL5U3Nsx&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1874, 48, 'https://drive.google.com/uc?id=1Wk0S49PNcOfSNZhBcTdYcMcLsSoiNwo5&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1875, 48, 'https://drive.google.com/uc?id=1WeiGfLcZxmUp7wuSIbCA18Qj_S-FlcwN&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1876, 48, 'https://drive.google.com/uc?id=1WYtTrBoK71KIJifoH00Mq6aUrSc3w_sd&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1877, 48, 'https://drive.google.com/uc?id=1WUjXgbPFapdLVeHgMKdW7YHs5VIy8Rtu&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1878, 48, 'https://drive.google.com/uc?id=1WTpMLVabGUt_Yzd4IQPM9iow4mbC3cee&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1879, 48, 'https://drive.google.com/uc?id=1WOeOrcTffYP_zVqT5HW0gahwd7o3agXB&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1880, 48, 'https://drive.google.com/uc?id=1WNsZozwUH8b8CwmUDOnwE8GvoFpNPhR8&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1881, 48, 'https://drive.google.com/uc?id=1WMmc1U7IeRXUDCudmk3nlgshgziMxJnh&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1882, 48, 'https://drive.google.com/uc?id=1WGsm5mKMx4g8bo_ucOiKqB8BQh1GtK_G&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1883, 48, 'https://drive.google.com/uc?id=1WAig64HwOz_G7uy6ifyQ-j1n8ngGl7JJ&amp;export=download');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1884, 49, 'https://1.bp.blogspot.com/dJ5sAfm-gkhEZvBojCCVObplk4S9n8hZL-FreI1dcoLB60Rj94aOUqkmkRAumELrIM42YRTpAqavkR1q_Kx4iEzEU652jbZkoqOWWf13NbMJFUEfEBtKe4H08gvIQGebJu3-aq9VZQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1885, 49, 'https://1.bp.blogspot.com/HXgtWQrdS7jAlEPGNKPKnfxpb4XX9i3r7hMlia9TF_ip9-p_NynEfGkb8ckl7y3zSE6v-N7Yg8QawLJMXpmeLdhUjX0y7Ym8axXtEksjO5jyYQOsAy68GLd0gsPqYRd-pahVdwRPng=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1886, 49, 'https://1.bp.blogspot.com/v4epz7Swrk2q0fHzTzdYaMIFFq5mnQqxx3tEq6xLNYkYq3ynF6c-y7s1xZu_InWZ6tWS0qSV2-_jEdVY9lZEsQTOntpR8fr8gKDUkyAbG0FTxEOeVjDHB1G2qWxq-_IhTyVRl_hoVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1887, 49, 'https://1.bp.blogspot.com/juDKWIIB_oJu7sQ-Gwgdi_O9tFNg2ZmH2TFpQvPcYWXdxOD1TKmh_wMtrKyHO01psEmLK02sfn5sHZC0sfF7o9XZZEt_zjLTD55fRrUhnefG1tsavx6GK2bXSSqH-eB-4HMJHA2KwQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1888, 49, 'https://1.bp.blogspot.com/KADtidY154-PD_AFzG3mJPIKrdgKbcoUA9CObJOEgyRwoC3VaCkxgduOn0px205HlnOpwGMSlHBTot82l9fz2AIkMgZsmVF0Juxb7sqC2USvz4P9cqJ_SNpJOlxvrQEmYPvMBEEFlw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1889, 49, 'https://1.bp.blogspot.com/cEyo5tNf7iVbGncKg-PeYFHfh0RFIufRBaPpH7mxcludA0Mr5hJ3u_P2DE5dgOWkO__02qiDge-OJfjQmKYkwv4mpoJKj5DVXlOeFf5L4omlfuCVzgw9zkTUhAg-uf4lOjyMZ5vKAw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1890, 49, 'https://1.bp.blogspot.com/X43mHyElYIjxIi68U9rbdRw6KQiwTMn9EYzX8wpUNzjHTD66GmhWsSk3cUJJhnKHwVVTCLOQlsNFSZbuGbXhfQ7j8FFuJtpxzNoza4436LJRuScngpZpxPXpp4zNt56dAEtYEe6ikQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1891, 49, 'https://1.bp.blogspot.com/-ugaW-6uB2bV5-gsDi7Xf_e99blqWh_rv4RiJus_Q9YhyEXaamY4Jys48JmfcqsMHB4KryvloDCpSdKcKDaFEsZ9JnRGryM8p6cGeksjPo-bko6bvdJF4IssfqIiUXbInoYX29bJ9A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1892, 49, 'https://1.bp.blogspot.com/VfqhZXc2tR03HAwsQqPLXc2TqS9oGzbZlvuyjmj1TYLIm9x-2efNyNFkepcFclHnQyM2HlWVX85O7RFouhDWen2tdIQf71NsQfev7ULvIB6Skn6UKqm3-eZgipH-Ow2fT8D0tONbyA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1893, 49, 'https://1.bp.blogspot.com/QWMfsOayhuN7lZkPKvvv7dbodJLh3iLFXgo1Irz5Ql9imPCiJpABOCSpENoEnL3ojWuWryo_H00IkOeZZa5T6_GT6Voyj3KTTaKW4rSrBJLGFV3q_5qqVl7fe3fGqAhjS26ymoy4HA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1894, 49, 'https://1.bp.blogspot.com/s3MM47SNZJxjSOf9Ecv39VzTurRU4O2OPRxCTC5DFqrMIW-ar41FAHid5WHZiSSzoeF4UUduySbs-MHCz8kWZ2arxrwtp2sZcVnTUocw1cEN4b9M1DPUSVf82M2SReAPqjL3p5DPVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1895, 49, 'https://1.bp.blogspot.com/oV-Sp2hOVn4j_KXpWO7GvfKDEFZA9C8uFBWONPzr8ThtdR42IaN46rmGKROiv6lsMyY_ThWXpFIyt4OKSvlqMb7YHIflJxHxb9O2iSNfS_j-kBmcz5z_OgIDUQuqz_vAnzmy5Li9Hw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1896, 49, 'https://1.bp.blogspot.com/jJc4BLC91ANxWUWt1mQY7Ns9Bc4sdY8fUJ3rshJJOsOlsqRyr0VmQ6E-itYz5s35udw477AGQup0FIvWhxx5zxwCrd21FV2zFmFvn4Hhr6ohwatwJAiDsFwgATwOIhzJ-Wdz2sB5nA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1897, 49, 'https://1.bp.blogspot.com/5Pmv531QwlqOk1Jwfsa_DlymJQUKWpjQ6D_uBpVEsKPmFy1qfQZt6rgXW_usCuFkIBmJmUE5lpIRXA96qPKREEkMW8HP6eHKapOXO5UWwxKj9xAcChnChOay9wxPImE65J73jFkz4A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1898, 49, 'https://1.bp.blogspot.com/Frs5UyGOjZjV2AxzPFw5eG5_msNX0jyZPUteomscDybcRqixCm5x6fmtDA1C99bsnFf60Xqy1rhwuux25dE_aZPt77iPmLjQN2DjoLG_Czi3lgq-ii91GeYtrm7mLqUnM-o-xPS2wQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1899, 49, 'https://1.bp.blogspot.com/UGE0nuEbn102P_-4eWGl6aCmZdu18Z_lDoqT2uIUmWYd4HdsCtEnIGw_EC-0M7MJTyBi2ruZnv_-epU1Wv6jWKlbRe8prq9jiGod-_k1REd7UUVla8Pnx_V87h1hdpegzmdiKHszrQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1900, 49, 'https://1.bp.blogspot.com/SWOdPfE3doPexd1wV_m9_Dw9pFD2NQ2XqVBNPasZQjAXz6sJiIkN-923bbVxDsaJxCvLANchxRKqlZO5pX7dxNvUwBb_4G8OA9Pd-tdcrSOtwQzrT4GL7jeEnCt2lUlzwAfgpCStjA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1901, 49, 'https://1.bp.blogspot.com/VrkdJ0mYZZJUdUxsdj67tVox9-dw6ZfmWuvJaQwVTlHfDUGCvjHEamBqnn7nAbdc3VH4wqWRAR8BmYpJJAJhGePHrSlXRqL7dExvodwzMtEhtEUUFyT3phM6XHGN9w8A-nQrDalwGw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1902, 49, 'https://1.bp.blogspot.com/E62rcCU9mC7nLTGfJm_UjB3GxWcJymVTPS8XloFkpuQB6cMm8_equM9Ubt4E7V63gMmVM-fsq4wSgMrMq6anmi8yj3DR4MwxIz9E-q1tgyWlF6IVnwv8Du3USJRml4hUzgprFR_3_w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1903, 50, 'https://1.bp.blogspot.com/cEyHlI3XmTjgK-MAeXYqLqEJI5b94csks3ePTgF-5xjJfOBXMeDSwlEc0jzZ0QY2MuLCVIuzDen0qFMy1N0F9mAA5U_ap_IZzlecxanVl32kjqUr1rhOB0nZJRPdvAK-eah6I_XWLw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1904, 50, 'https://1.bp.blogspot.com/Xzrf34glZXSSU9ttjbkEv81lgCzTUh-TBDpWHeaEbNkKRQsGNJ8TxxC7YKFqsdNgesT52ByL_Z6Z3ihBL1tR70cc1lhiHsUIdnIzd6U2BTvOA1Aq9AozipOcy0Rd0D0zfwcdSbIDhQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1905, 50, 'https://1.bp.blogspot.com/iyzhwQuciINGjTnidwAOus3MG3sfuTZW_UsblX7Ipr-LaTp7qe7AvQ5pCA3quK4K9Xc6lq9h-EvKTbJ8z12QVOF2iBqzEQ4cnAYzxufR5uBGMyaDZmThKO40zS1zHN5E6gWGTaPxrg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1906, 50, 'https://1.bp.blogspot.com/RgsWhJTC_bo55A6B1ILfTVMnXr1Pq0ajO_KXct6QJjJX64Xnl8fNeocuWeeS03Csd5ahbs1whCGjpmpLC-DNXs5ONjM-X2KrFT57eEdu0kiyyxNg-BOMdKxh5IFWPMg5D_ZzxNFnDA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1907, 50, 'https://1.bp.blogspot.com/-jBUYHrLMnIfE8a00Bp6U1b1FY7l3sFVIeYEb6nAPiVc06TDenx8bvzBoxhy8ubS4Xw3giOvKxe7COZGVxv2g065IJoOhEi7B79YchFt2SUTs1d-Gz791s-sYsAZUT2QYcPew_xDQw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1908, 50, 'https://1.bp.blogspot.com/D2DBRQ0WhJt3URRGmuprbJPjPJF1Xg-GKI1quZZLab4OEO9icDrUAeGj2YHrhhYAyUKG9eTbRDPBG5fgwyV-LXsIuy-CyrhmS_DpqdJhSOBw1b-uG00OF6NTF1IpsYMO8m0SNFunvg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1909, 50, 'https://1.bp.blogspot.com/RUrSp3qPFoBfkMgyoiUxja7qeCp3RpioZi58LWNxpTzOilTtoGIdFOZqX4EilF-4Yybi9o-yMirDHT4egnpADTEWhw4Nw_HZpMkwTZgbpX5QS2bwtzQ0PwNKN7gG4aptNGJGOGAjsA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1910, 50, 'https://1.bp.blogspot.com/I8nATJG8EERubPVDY_wT0DV0VLtW3zssKcblvJ2vrlznibt5x5nhdEEfCWu3y8nYdNxVRg4NXS7t6BfSu2CofY5S9zeCV29dDgomtuf4K8xObQM7uYcb26qbhPGOforfyZTNXOhN5g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1911, 50, 'https://1.bp.blogspot.com/DeoV7ByBGzbJe7beoo4rcG29IaHSoFrYpgBSM4mPsnHJB6-1IzCfk0OXiwFvQYgL7sscbJ20WorEw8ICPJUDTfFu0R-fXYmZWKSZHDxTeHNzkBGprEnxjc91hcoC547fj7l_yqRENw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1912, 50, 'https://1.bp.blogspot.com/o7rWD1s9_Q7qgoX6RfPNOpLqq2q2C8bkjLWABqSOIL6dJHANf9xI5xhlCulVIUabyqrLAuB5MUxyzI09YIOs-8QLAqFPlrWnyCSJZG_vih7DZsrGScfq9i8og5w_WGSnzeEbZvROXA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1913, 50, 'https://1.bp.blogspot.com/cqOgs7Lepf5XvU1aljnoN3bQALAphNLLvEZR_RM3FPrvXVmM_Pyz6bjnv9R-HWjtcZh3f-VCxp3Tg3ovp6z5RyCFi8Hh0vSgtyuUt52L2EVzgNzq-blkQarAYKNC3MsXDET_NAswEg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1914, 50, 'https://1.bp.blogspot.com/ItMnQqnxhhnmkNuXDlO9mPR7NzTdv9JRLa72CYSNxWHLW0nTUQlPq7Sq870tyArGKFDq7OBvKQOKy1hQHnZwfgTRfDbWRqrtuq_dUJYcIFNemXpTruqaHtcF2GYzVrn3Q7te_cRr3g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1915, 50, 'https://1.bp.blogspot.com/ifweUzX5ORUuQRqZv9jve_xojP7qu4717Ifnep6PWQyDkeP_ejNYXewwIuvbfMkrM7pobEon5_OzX3hR5GXKipmINZffl4V5_nQMeZ4mQ-k8YD0h2A1xQI5mFhO-T8SdB4NWa_FI3w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1916, 50, 'https://1.bp.blogspot.com/GqlH9xMH86Q86l_HcSbTdd3-zQufy0LIaHv8K1CkDvuE8SaPwlvz8rzy9_QKSxiifDFDfirBoWYYeUZfIJeeIaZCk80hQbbJu6xRun74JvUnmQ2HUU9VZqYG9NVwEkWlOrlGuDKepw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1917, 50, 'https://1.bp.blogspot.com/QkyuVhamxg8ECL-GXEv2tUPmM2kHqxoynuXVy1PVmXQNvs4XXP72zBPJKVyRutzbF6RwYFJmOgJ3gZ6sfTT2xPePmag98pTxNTAmHng64VOPpT_ZQ0JhlvoXUetyxocwbpLd275Khg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1918, 50, 'https://1.bp.blogspot.com/Wukk7-oGz3rg09WEqd3opa3hwTdBwQaXf_OWej5YLJxG9-_Z18Z-h5iQ4HxKDu87Z4XQOIYIxDZJlcYBEXP5of0AYuQuPjxE38vSlj7Dpu8VBX2x6DxZCtNHopOXsejUJzyqYx3r0g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1919, 50, 'https://1.bp.blogspot.com/E3up6tQlbwaPHUtTSFQi8ILyGFIs-TbLCj3SFeaIN8reZ9QDWdqQYjaSN5EBKsZEc9YTmDfL9Xhk_i8yj2l21G4qiUhr01GsP4UJb0vhD9c_2568k4Ej2O8UqKaWzuo8MwFvIuxNzw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1920, 50, 'https://1.bp.blogspot.com/Mhep1YsqW0Ey9SZup2IUkzTjysw4G9S56QN4x8duiAIssBlhNCbCwjeBdNtSGyOmYQSyDsM-ozKT6M6C3F-d6PGxVNj68K9R9eUPdaL67w74UjizCE2JotAsjNY9hPNGs1BAT2pAhg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1921, 50, 'https://1.bp.blogspot.com/P9kLkmeEXW5lb2ziYj8Mv-ZXEnnTUOG5vOxOLdsKUOGiwljsydtbkGKh-D1y7wcW-eJsNq1HYCeoE2nYDyy6ylSFfeT8vPk7Wl3N-Kf6Gq1Fzw9D3DKvV9Fm3XwX4xgr4bCoHoe-5A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1922, 50, 'https://1.bp.blogspot.com/BnJvT6ZvBOeZX3OGnQ9C0GybLbllIJSlw70M5AquPWtS-rcsDW2TrFrZbTiJ99l1OZ3LWfafZ2KEE4IyjnrlARfvIqZTlo07Q-ZjXvaSABckfYIhZbwcowa8jITo1s2P3jFCMJxGzA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1923, 50, 'https://1.bp.blogspot.com/phgBm054DJH2FKiqttFi3ptSBQ5Goyh3pKa9GRoJyGiTwSFlOSColtOYC0ThV2sPg09zQQev99vd6Ljr3xdQfRdbTH9brEuUdCiGLs-z5yLQcE7DTi35l1owincI2E5hZZOFf71cvQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1924, 50, 'https://1.bp.blogspot.com/SzKaLyf4pZ8V9IR74v_vvWs1IqU4S0dokNtOyqKYV5Eid7NyPbsr7B8MVldbjZkQVQK9w1OxF_UPworD6maOdCqJ3w6RHC5VZGpJGgPbJIiWaKAC2MT2z-Jxyxn0tqdGNWPiv64hQw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1925, 50, 'https://1.bp.blogspot.com/k6UVbndvYAesC32ggadV9TbJH4y8pq9PcrdmDoWZFHPZjBjmVISdPrssD8iKSbbkGlhVl8i8izTryQ81wrfVteH8mVmvWZejLwc_UlMtb0RQfAxOettPpScBGstrbW7boiD-SNdHgg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1926, 50, 'https://1.bp.blogspot.com/dqfr0VYIRCecYfz42DQexQQdqcaZj70PxkAzC8zuNfTx42SoxBPa5UYrl4_ozInlZ3FR9SRBGv1OEOX1w6Ek48oybqRCAvjiPXkTL-fmmQR9nOMuNFNrhEdCx_VKppGfHoYCrFIHww=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1927, 50, 'https://1.bp.blogspot.com/lcLW0SgP5pB0oG9adP1YyXB9q3rUHY3HO5sE5gW5qmSj5-K_RAx2FwkRaTlDTbdPFWPB5fI8ExLs2Vzu1V5VVxpoBxT_127AwXMdYdTfzSupYvcXie-R-PNVYj33NNocdDMRnM7teQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1928, 50, 'https://1.bp.blogspot.com/Y9gCac8Vxr518-JWkv8UrE8CRnj4Qs-5SzfaTvzufr7Zim79CbHbxBH9sOVxEENyszmK6HTxY3ZqDo9l_kPbUrHbv7Io229oDJYReYkdLFSVd525gnhEd1aA1kyz2JL9b8GMsyJK7Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1929, 51, 'https://1.bp.blogspot.com/iWDrK-w7nCXeidGWe02jFZOCRRaE4ailM33cakOlJvWY3pEYCo0gvVpRJGUjYpIYSao_cW_YGdKBYq7EMgOzZAg8Jme-qsJGU49pyqkNCGj10UA_3tx5NXVNZB7v1VjMgDZhXA5fFw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1930, 51, 'https://1.bp.blogspot.com/NFwFsZvmeAQpVXiD_nK26zZIOGs68G4vJuonYW8nllVkoDpvJ6VHNwieGGaxf4Ox4nLSXTY5HBnXNdwHlon2PKA8VxHXrur5IBecHnY1gY4GJEG-eTc9KKUaDpmjgGxIkFU8gc2oLw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1931, 51, 'https://1.bp.blogspot.com/RUXBQ_RUMgVexoaHgNy4PALtwK6EDoFtdyP4VsfyTUy8pXnyp-Ll0E83879sXlEzn-3XSlvAnUlLTG0HQkmOie7RI3GHtFUMkqsIREluW5w2e4NTiFqEBXlVOZVRzyDuGfYjjGUzvw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1932, 51, 'https://1.bp.blogspot.com/IoG552HmftuOQNeYG3ojZ9LR6jZV-Mg7S3bgi5VsLSQJckXbe3MUPq_Bms-p_RaeRbhGc1m7DfQQ4GFy9hieXlmAcHG_yuvWe797wMrqrWppV7bxh5EsBpfNPQvZ4-as-iWHMNPj8w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1933, 51, 'https://1.bp.blogspot.com/heUGYN3JUZt3CbZZkgZyPKSrQ_Yz7yyRj-K9coSY-KvktSHRoQgzJmHh1CQp_qRa5zqh3LM_dqNRe1Uah8s1FYGrI59d-jhpa8m0AVCu3PFxoZjJCupOQYtcqX5ANtC184TuZMuC5A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1934, 51, 'https://1.bp.blogspot.com/kPhj6MI2ODsHlzrnO3uzEUq7aFrOZ7r14LWd4fZz0NsbQWr8u_rka-fmJqodNXl39b8IB3nUktu_D_Sgh6DtYYDohUsmSzNa3xAsBVsiVr7SnWyNQTB8tAzxSg6cBFMU8PH-P9_xEQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1935, 51, 'https://1.bp.blogspot.com/nX-dlEAXSiV78_tRfTpiws_h0ThUZ_jwGTtuK_Uo6izeqQYNqxFF7WVZSgl661nA4pW5Hc0mKeY3aXpcue_wONejoO6rnmHgwpQfw3nR-bgMRNWhdU8iHqQH7raYvLRikSvTgjHRpQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1936, 51, 'https://1.bp.blogspot.com/WyddGwb1lCHVgIvH0dE1OD_YGMhlzqiDk8NDlqG4VJ_zyMFnTXgElKTPLtXsE-1xbrpIcCbk3_1503JrkuN-ISZ-D5Dijvsnq1d1nbgCjJitkceN1onC0_FsMRD_NBw6czqEEW3aVw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1937, 51, 'https://1.bp.blogspot.com/gN7bEdsx6Gt_Y9lRK0jPHPeVYfkVRQndspT4S9erPXJ36IFFbHbBDrYof2dvCyzJyzidZXv8QmagVzW-6XIiWKRDKRr5jo5oFHqN06ZmDtZCb6ftp2c_uBz_o03kOfnKoDJiWaQ8MA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1938, 51, 'https://1.bp.blogspot.com/Rck36d8u83Wvy-B05oQzOmLZJj136UfHJg5kWU81EsZfUZ_QW1w3gR7bvrO0UuMbNWvO9NiEBV3Q1qRDaO9ToRRrPSuQl0i30bWbT0UrM7gQY9UgiuYIVjLwQK44y96MnEKhCtHVZg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1939, 51, 'https://1.bp.blogspot.com/crh_uurJLh2nDMwg5oNv10mIyXDFwkbmqFJPI-EXE6DqbiKZe7X6ZKhQvxUiWQEHZDtpSxK_8EUf2_DXOYoHSQ25-kcBLq0iMhuDf0ekcLnDS3MXDLSP_JzjUSv-EBCfImMMLPdEuQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1940, 51, 'https://1.bp.blogspot.com/rJvXgjP-PmX25PAASQNFbD1134EgzUf8VZRdJxXecYKaWl2Brm1pqf_S2eNBKZA6s5EhjmmWUBQmok60giwl879h2Mq6PkbhQXcuAMDaLLTBorn-zYdGf23JkqmzCCPYybm9LE2EjA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1941, 51, 'https://1.bp.blogspot.com/9KrtlQ47oNwR64lxK16gQ5HK65JtoPc4A98Don1g2d_Iwt2pGhEKw5Yvz_qioT3aZDgX7Je538gO1Ruq9tLYb8WglRJDU8SOperQ_ypT1Sjwb-ECIH99mC7eWByYqsaFwQ-DStvZaA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1942, 51, 'https://1.bp.blogspot.com/I5R9fsnbH0L4shvdIBF-3Ji8swt0E2EV9vkqwYix9qvxHR7DK0n_0Vv2CazDgsIVuevt7iviAeT3f12VaRD3WDgjNyjuoJfMTRTEUSVTbLNC13hIRu5Zf4gG0P136vFXOTLG80cnYg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1943, 51, 'https://1.bp.blogspot.com/83OB8wmFFelACWin63Ekk-_YWETDdJACJJ70rvS3SL-psd56eYi6TAdUC2zzlRpZwLvs25DVR2ovmsJ3ZSkMwi9VpeFwSjrRjK36u6MpVbsE1FDQmboWe1uirCdXmtbgB5h94aHN4g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1944, 51, 'https://1.bp.blogspot.com/2c8ElYKR96IDTqzcxJc1N36w1AzKsUD26T0_7AaH5dgZ9lt--4SCrveujRSBVHAIUQCkzYX2T6P0FQ3Fn1TOLhSK2fkrdnjos9F9RjwNsaY0SgLjdD8-chPilRIAQLvmjEU1E6f8pw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1945, 51, 'https://1.bp.blogspot.com/dlRmjag2pFB_JcnEbfMoeMqDfmzO9KNHTQ5nb75XJ7JyPBcLktIMP1Ev29tF7eJwALPHBZZFOsW6Wk1NghCaueDcId_CgllC-biXEBKObQGHvk9IVnwteHie1Q5DlSTU2xmkzbY_dA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1946, 51, 'https://1.bp.blogspot.com/eCH6KFooPkfNiIwiVrbPoYF_8APx7j9Dq2HF3dJus0j7ZhhcNB-kkT3viqxGJMMX15CvJ5jr-lo9Rbuzp_VPe9rK3E48jQDgIeWFJ9uKrFNOExdK0u7n7Y355ML76Noqu_qeQV96Wg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1947, 51, 'https://1.bp.blogspot.com/Q-dW3wYPG0RM7Ldar9JgNaDnfNbI5ZtzDRhm7va5gvdnKTy1lYsmc7IDcxQAkaMMsFbbuJ1YK0Yrd-VyQ41UiCiAPY-_38lTh9QOvudogfB-atSKv7P6vaaOHqeVOt9q-3f7MhY7KQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1948, 51, 'https://1.bp.blogspot.com/TFGNbkKjQh-Pc3-HachqX3fVJHMxZTRxQ3kkjPeWYpsxvg_9aNwxvAc39p9dt_U0ejT-dwndgNAR40GqaKoXkQB8IE2FW41o_wZSdXrmSU89Wpu3HmIbsCAH84pUuLB4hI9-BexmWQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1949, 51, 'https://1.bp.blogspot.com/yQ7SNQAHrsTQFDhycfeQsNbIe-UNIZwv-QoJjXboGAGl7WKcViAkjMP_ETLwgRJTygissRg12tdYpQmCOfQ4dBzfBPQqenZx-en4hWRbwzhYFo1Snd6ZGQPvOYTboIVap-Wd7MmHYg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1950, 51, 'https://1.bp.blogspot.com/3kiG-dgDamtRZ0TXB4MS4DUde6XNTr_ezeXgLiMb49Wuhq4MGzRyAyoYKHU_JyF_kHlu92KcxDJVjOurI0GiWVbn2QbCdA3BCje53TtDgskeEM6Fdan-hwIv3goQlEQURkGBefgPRw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1951, 51, 'https://1.bp.blogspot.com/qiiEBzlTKn7DFVafe3XvHHSGHbRYT2iiM8AJyRX6UR9grIdKX_Y3v7Qu-ZvqwfG3c9GKBnviw9Wxzlp-G7qt0tfX9a_ZjMWz_ZtgHeolReGUoxcgiW3QXhUAIuJtxDNeHx2sSHK5qw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1952, 51, 'https://1.bp.blogspot.com/TOuvyCezUe7fHOnT4niP4Y_8jQ2IJ6g8C-p7GW2Qm-TcXKvB3tpbcy5WiTbKu1DpAOq3NqjV3GC-t8Lcet562PRvx7G9rJASwSenIk2LzLjvWthWeNkZWZ7JjeNfc6IF8ok8QOV9DQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1953, 51, 'https://1.bp.blogspot.com/sR6LguxSvGz3Rujs-BmeVwu9bcxr6dVuTqGboUiRLH0l4WmTKuhPjZVtQfhHkbfzcYDqYiWiT7TJ6rWlXmMCmFjyo89o7UgBO_rMwINAZWB9PNvvZQ5jGwgeii1Rgh3ZDi0Q-Gu1Cg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1954, 51, 'https://1.bp.blogspot.com/iqQ2ppfDKY2MUvghIyTG1tHoWHU21MKRBHC_HKFZxc93l-NELSE36D4dLJug_xVMsVcuHlvtWYLYc0Leic-VBdfstg7es4ZMTGxV6U0ONnTJDR1dSwDUkswq6Y-ac8ThJyLK_8f1EQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1955, 51, 'https://1.bp.blogspot.com/hMGO5EM1Z3-Jw59pl2KtyPBp8ksaGyiTy--pOqvSy9lBhhGu8EkqB_oV7dX960gxAsM1_JKdyqBBOWeLxs3mbldL3INbbW1xRA87c_vrNcuszX5HTDDzIT3YXusWC2TUxwM6XOkNVg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1956, 52, 'https://1.bp.blogspot.com/EVO0JEx_lXWBnvW4YNQ9jys0pNl_uc75-t2iex_tPI6n2MjYaVoUhYfbuauxqqyae5Iokt76aSSzmT_b2v5CsByfpy6o4ikak3cTXQJhXBih1dqSpc5jr4APPqSyNdgOlZ6aKikAr7Q=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1957, 52, 'https://1.bp.blogspot.com/Xtve_WyG_in4fF5R5EhxKPurE2h98UG9RL0-XUVMpLNLfHttcavfEAbP1hkpiIfWcMZqonTNTpzPZ8rPawOVnrZXBfQ1hgx2BR-J7Uqz0Wu51e7BJ4hUn43RR6DWpU-9uGDxMP7-8-0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1958, 52, 'https://1.bp.blogspot.com/SZfWRqL1mXvodiMIrIZgClYARYqa40Ju_eOoWkeIMiCNhZSNS2l3l3qA3zP2HpkYyQOme8MW4w4tF5296yRvX2UAi1ljYbJZIdXV-Smh4wCmoWI4JBBbhCdgBMcMMfS8v_1i5FMCBE0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1959, 52, 'https://1.bp.blogspot.com/UFZaM8UEqXBbNRzPwZDNQmndi8RmumEUPAH94AppW2nkPq5sFhbMxO-ew4Kg7kr3D5QsAO8zPyoN8bjV2lm2hZ9iyFVwGtcrdaJDHdPWMfWEZ29qadCC-aBbjuYFcK3vjIvIF9d5d9k=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1960, 52, 'https://1.bp.blogspot.com/VDQkHNkFgCPuIFsnUArYrND2gdKB562AWU59NUEiWzzbDo14jkrQEjn8FATWTiGqg0f_xxc1LYwczZ1R17a3de4FE_-HOPgKjCMmojsWw5FXbvOUWscH7U3-6fNGyf5_5dE1nGByjOM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1961, 52, 'https://1.bp.blogspot.com/OJ69D2fIvkI3t-pAqzKOeJjKeP5_QLynTZzW_qmYpS71Q2e-n5VG5Urq_SJjZII6dRo3IPMJyHHFY_OfECTYBucJysRrrxQW2Bz57w5yR5BDNVzUHGlWxu8T_I-A-kg-pb7FSidD6Wo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1962, 52, 'https://1.bp.blogspot.com/KjwjssFOrKtj-idrLx_Hy3F5g_HBVrg-z7hd02HxwZIYZ5Jv1H4ONaxL8P_jyu08vf-LIT8kbR2F2C9247YkLuWn9B2AaVJLlMGkkJcKdYY0DdtkIZNgsKpmyMxaWuqswVL_t5aAL7M=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1963, 52, 'https://1.bp.blogspot.com/uNYz4ihZ46_WkJuyOnnfeKTwIiPD-E0GZu_s5SrCHuwZ9Ok4AE8zI7X1d67pE-kSPoRvGRyDsUozYXy_yWtZsJzmp34lEy9DkqOaiJ64JBEoNhZUqm3B75vRVt2M-xUfAjmCR58jbjw=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1964, 52, 'https://1.bp.blogspot.com/yuAL8rUgxsnfZN6BC6pbeaZ9kYj9zidRnHxq8ITl3FEAQSi8dVbPGlzQujHRKmCPnVDkuVPtTkBivRy7QEDkNhpz8gHtJUrFgxRPbhA8TSHswR0qnk2PraLSDlksVQiym-FE1phyB-w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1965, 52, 'https://1.bp.blogspot.com/DgYm8l0N0mtq6XX3R6h_RAYVATyNgVMxRjsdSx902IiIgPrgJCG9ACkFEtAf49MMCrkuqMbPLovuEkwtKGSldO_nAFzCkn0xs9e3BeNHtO0FgMp4JAkU3a4fab6X70NfWx1Ox0PX7Ko=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1966, 52, 'https://1.bp.blogspot.com/_Te17Sua_S6VBdN8X2PI57FXJOaHY3KgaWe7EvCfJpp81Z0vvYPvVUkcHgyAOtu83e0rdI4BLoxJbYhZpu8gf_EYWbHW8VCJo1Sc_mJSmsxzUaIp3SGWwZmVLMX7b7OWe7tz8vac4ng=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1967, 52, 'https://1.bp.blogspot.com/8O-zsySYvRAY5NVpd9in0taGKmDAZtF6simNO_tARHeCUJLmGi-1gh0HgMfpKIWzEeBJTcNOi7zLwYUmgYnCYqDEe4zHoBlUM2YM_U--7ttlOI23cnekO0UidAkRb2jhJT2-qMZX-4U=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1968, 52, 'https://1.bp.blogspot.com/RJ3-DdIeJqqKlq6EsMra8dzTMiEND6qWPhActYmV0-ic7Mcl-GTZemh_IjTYQaWCeP2WMEtelobIBSfQmMepXBz0G8c_OryLPenxXo6qwzRQHuoZ4KvAA6FWJo9Ti5WL620W6y3u1bc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1969, 52, 'https://1.bp.blogspot.com/KmAdxaiJeJnblnC3uEorNnfygcIr7hH0Ma0ArPgoFyzMNWcvATx_vNjHWUMMjhHHl4wgjuo9t32oAVrSAmkF0FQG2v9uSstEatp_TUaXPlTTiGYe6J0OZFu6U5cO6Jq1MpvZUq96F9c=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1970, 52, 'https://1.bp.blogspot.com/7CLHBREfHh6Bs-jjB1bjfomoQNHyFuefkgxs7aMwNQ4UKUouBjc6lxmtFnHhgCsZm_dsFXziijJbYvffrfqqbPRTK8jUOi-lFJib319hE-qdOGL175SpqR4-fMPEvvBQo3SI5I4GeTY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1971, 52, 'https://1.bp.blogspot.com/9N7kFZSYw3UVTaNL7eYJ8HqOrA8wBgm1vBbMrCjPH-2PeBEYOsDyQhVVXQe3egGyKAXOM6oPeogeSu1pI_cIVmYFs-nvym3aL-2e9rThousOojxDiJtSNMTFZGnb3pc6KX35m0vV8BU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1972, 52, 'https://1.bp.blogspot.com/JKHYk3t1A3o25hcX0XWYeSwxdhsd9yV6clbik6wUk4EhJkJKhSm4F4UTAXGT6IMK4wFaxFbwK_SblT3YwlmdBXbIIxZRAcFC7rxihqTfsZTsNFmeAcwgdsrBZJ_lvwGN8dzoQ3m5mQ4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1973, 52, 'https://1.bp.blogspot.com/JXhoOojlJhWNkzXa1XX_qYFeqYWahCB9IbQh7QXMiAoselFLj-UttvPBXf3uJftYAFWdPtUEQjqQYkvVgjcxVmW7vZfh1EZQTVsIxBsea20z9e7WnuBmryu2AMjF5EE6NK3mD4nd7GI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1974, 52, 'https://1.bp.blogspot.com/1X2LpFdkH0xhv0r25IRTqfc8XAPSMVy0AoluIwY7tXhsiwJQlLMCTtcN_oh_9o1dTXIoDFI4ORe_IaVkVqsfVDcaSgWpsrRAd2vct1RVJBj6g53Nik_YtjNJCFjWpNowGuawAN05AiI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1975, 52, 'https://1.bp.blogspot.com/64PyYOBjzBCJoLWfpAxl2wMoVlG3o_lDUpHWHuknbmYex-oQfkaYzPyHNHb59dwFykDiBpD6QNZDRIcqyDedisLUK348UVdxYcgVUkADUtrB45Mt2zjaUVUE6mE6S2uAEhKMenlbQHM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1976, 52, 'https://1.bp.blogspot.com/x1LhrKLQAnOSCkffzSdI6LZYeGmP05jfK9SmH9c7T5Wkcu6iR-zYop5DbayTlKDPQgg92Vs2Y0EPwNWnjXmxJh6l8dnyvBy9Pq5ZP1KBhwUxzVJtVQ5u2PVUFe2-ETD5eG6dlh6l4IA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1977, 52, 'https://1.bp.blogspot.com/Ec4zIyKosqbJRqguBKEX47JRals1wp76tGPa4UYoWAvIvonckUNAqk3dA40M5m14xajBQ9WOEsSXDevjzrCOnWeWk2epkFXPDX5A_3ge2Rm1-U0wYuDJXcHDtb_jFQRVPxrHEfg83cs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1978, 52, 'https://1.bp.blogspot.com/htKNi5iY4jHu1QVQp8gUb3lvW9Zy9XHeXsMeIshkZX88MQ5RbLhd_dJXuDzFHhs_Agvrhe1B455eqsGVIP08xb_ut8oTIP7W3Kw0H9RTeK8l-uauqTqKAsQP-LW6LwPP1gHA7bn9y1o=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1979, 52, 'https://1.bp.blogspot.com/wp0DDFGRmGQ2zLxSFZgBSUK_3OOqbcLgVThrr8Vu8uwoWtpLqxJkZqmbIhhIPbqj9ZfswE1U9lGTExIU4q1SDxnzN8FKRnkAq_u44eEa-Bg3lHRDwZMNeiM4uJAy-uEkRP0M8TXPsVQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1980, 52, 'https://1.bp.blogspot.com/tbMupmWXichvAIAo59B69OjJZny5jVAwjL3v5w7bvi92houkb3lXXvmvjjZgsgOVI0FfHv8aK8C57oEEMhgwr8yGMRr6m2SNBcTFJ-umKM6ae7LrM1IHgBKm5gfOq9rKlKsqx8-RWWs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1981, 52, 'https://1.bp.blogspot.com/P-6ywMWlIuzsSPhrwdWS6oimh8lIyOmyZqdv5USKNGEA_S8wO5RZNuC4R0qmKZ3c2QSqAG0pnE4kU2vTepwrP9trpJZeuvsIP_y3DRm47EE4I_cVW_vYqX9oIcxqu739eS8J7DR3o0o=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1982, 52, 'https://1.bp.blogspot.com/YZ249XV1XioCputYm6ddmHtD6tMlGOpyoJNQOS4eCb1mU7m_icjq5bhtSethOEwgpFL7jouAVcK1RRe2W5GxSZqQhaGg0UUHU_uk4E77pBG-UGF3luMTlxPbF73yxD5WjLkAHMzHI0M=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1983, 52, 'https://1.bp.blogspot.com/pAG_611hf3bLkKVZaa2ROhboOWlsTJqjYtD6wYZJ2yy0DSdXsPZQN2rS6QG0ePRH0XN_803KYLfwL1UnzOibd7CjhwNCnnchI8ptCqW3zsGu4OonJ5oa7gpx9s0MVtPGqqpYZFrILa8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1984, 52, 'https://1.bp.blogspot.com/A5PCqTj-nl9zgWDBv7RoY7ubb4qBOU0XJcVQviC7KFlXMcR5qC25j-M6PbGJ1EXR_4Pd3HXPAMUSs3C5i8JuZuVgfK0RTcIjj_KtmP75EzEaefM_dFha_HZ2Ii5j5eaMAqnuusdB7x0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1985, 52, 'https://1.bp.blogspot.com/ll4mSH8m0wX1euexZonllQplAy8y3NCzeX3CpW7dmwtLmFt4UizuFe4H6DjyLQvgTA3Dly9sVrzW7IDca07XDpuVUjpAJrI0E_MLvRFu6xvLllE5wJB5AkwDl0-h_x2zZy5kvYM0MuE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1986, 52, 'https://1.bp.blogspot.com/hd7kTzWD_tZLOtGA_mRIw40UIkkuiY95DExWYfnoTRYLLtFPm1lbJsfVDUGD7IfrCw0Dq68zpSdIjHMLjn1WkFidFdMu6nReFDAYcSilGWnq-ERm8h5_0W81TCNgmrsE_4uUTxfeAFQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1987, 52, 'https://1.bp.blogspot.com/6FxKZet3iow-r7Hfhxd8quh4GQPsQq-J6hMET3xfQE5StGNCXYTe9BFlnwAul-O55Q06ufLFthJhP2K6k9GHPTc1lq0J37mFSoCRdJvbZSCIxCAIG5uGPBSGxPGf7qo047zq2RBcuQI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1988, 52, 'https://1.bp.blogspot.com/e6KGkDxVMVxPpIPrCs0pyC6mKnDT8kuwbw36bFvjZ0WXFaQrzVcvbpykntfldivTkfc5yXJWbq2QrnYvalJZAx_otD_1DKB0mOJTFbesTw342di5K7mPHDB_94PNDcWr1elz2eWEwao=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1989, 52, 'https://1.bp.blogspot.com/rTzAziZg5N-ElbkFKvXqY_LzReXPYTD8Ivcy6DPxqSN93lejKsAnWdQpfy3_GqDWF4Oog5s0US_OWp_QXzyRbZGSw6UpdW6ICEso5DHPrF69Ag0u3X7JUekGvxrGxprJSwkal9sJj88=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1990, 52, 'https://1.bp.blogspot.com/sDckZVNnfsX14t3L7nvXtiLF7ZtWwjNFBdPbYGJZNr7Fmw-M4v8WwvtEYYr471ZAI3gTZEirwgiIYXeI6XVk6vzBPM1Ww3SUTHa1BJDRc3nBXv_Xnig0JPT-4FJrSsxevyZTDqQcoow=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1991, 52, 'https://1.bp.blogspot.com/5hdebIS_xkhtYDsi_ZOAtDgNtI-GBvXP2pwHqHZkiiF1w5Chdh1jiV_u852VJyhonf9tCeO3o6WrQagqiwUdA1k0caoVfKbcPCiAMhbk_uS4_lhz4qk0WOKHjkahdyuZLHYaf-nPm4s=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1992, 52, 'https://1.bp.blogspot.com/oWXuKeNJ4kBnn_BFlGOuDd-SpE8i01m0ezOwKeutUf5iERFKjQM8o4Ajj9-LmsbEkIbxqbBuPo7ke6MY6s2qqNQNBifBvzmY_UJeDY0YIC0WL5ANh_k_FY0YVyfgcHyAI5VRoUV5T0w=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1993, 52, 'https://1.bp.blogspot.com/a44aiDNP5SYmABxkR0tZzMNdPYGRFM87DwWp74VHcH68eYAvLsgf9haMuAZGmUhn5TXLh2-8plcEceVZReAe83leiFhppz7Q_T5fRajfLVANxq1TtmqJwIRQhcL3N2QrX4sXdhwpcPQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1994, 52, 'https://1.bp.blogspot.com/wB1JJU6jjgT9mvRIuA3rzsmX2hcT4U8kP5kKhBLv_5ryHZqDc8mz36rsx7G18EXaQkXtmIvE5eX8xtxSr927inVe4t5vf-bREbQIYIC6HA7TLEeR3g7omsSdp_sLGo1yg5MZHoHUuE4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1995, 52, 'https://1.bp.blogspot.com/dB4G386kBJDCO6Rdm8ctHsXsfmgrNxJ-8lrX5ytcxexKw2KI-Emb04pu3ODdUy1dR20Q6eDaDV10e_zQulLPTbIZQBh8PXtxhLnOYHuZDyW4r_NHGRquTlv_uST4HyTnk_8mfhZg3a0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1996, 52, 'https://1.bp.blogspot.com/t_2r7N4qvMOriEcU5gMjf-s3Ta6eXzDgavhvH3AF-hGTMJLgYubidvhmYOxjZco5tApYuVweKjp7_OLYNntHfyjkAgzgWQuba-aYj9Wr67fVdPS4uocx2AxyTpi0Rr564_LTe50HabY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1997, 52, 'https://1.bp.blogspot.com/Ze1mWhI9HGiVO8vFW1Of3shQ-Pu61dHWNSTBoPY2WCEo0cn9IvOnex0_m_FMRNp0M4xGkbfvYoTzm8KLR31HAfSy9MdXNzmDvRDlxuPu7r80SoXJVvbyII6SbXIjP-DkMILLQ1uVcS0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1998, 52, 'https://1.bp.blogspot.com/vyyGQp2OImjtUj0ZPibzUKdgEal5RrpzNrHOFb9rAhlTINpcRk_RlarGrtYvjMeHOBmxYfjf5JnNcqv4Wk7xNNBSP0hvnR12l3XOY3AX7ocNjJMXxT2vJs9sNiE0a-t1QR5p_FnBhvs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (1999, 52, 'https://1.bp.blogspot.com/GqPmQw-RuT3MAXXK8qDDVPCtrmD-B69azVAOpwjLPfcbAJ2vgYuUw2wBTRbonLRrQytBjvbEgFP8PjPxnT3J2sfR7U_Rf_Ow4pF0ddAUbeKi65FYtGS7cU0yOKimg1xfZBI9uWMSDH4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2000, 52, 'https://1.bp.blogspot.com/JlydGMHvAyrlU0EgB8yZFjhf2JemByYwA7PVbhKepn64y3pT9GLTsOngiaGyhrrO8_9UUFOYEIa3gFAkJQ-2y84IQtDP5eorNUqjIShHLel1m8tGscnjeuBSk-uw8jNhoJAkKJE3aRo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2001, 52, 'https://1.bp.blogspot.com/hDjlYi0d7Bpum0yrG5DAzEaPUhzeNrre-MSGahCkEnN5CKZMfYjQY1Mm_mcWrnieKcSWuE4iwq5kwroGsAoppkaMy97hNzNsCDOTPKpQTDpkKCa77Y4pG7N9t8y32HKM9qwwhEb616g=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2002, 52, 'https://1.bp.blogspot.com/N2uWqXGGWa-f2lvrhxnaQNcll-Smy4CAYaHGGKv3iHNLilKdL_-rv4LHNmyAiIOvAYShdD2De49AoQmaC71jWfL9r3qVW0UJ98OMgj6m90um43UJoGR9pe2VcehE5E2d6JzdPtKmRMM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2003, 52, 'https://1.bp.blogspot.com/p67BMp21MzsJZN-uxJZqv4fmgHgsz6SvUniSzXcWr2jLNtnna17jjpWKi2LPO932AVZWPnFlYbe1kApyya_G39aH0cAlVyFFa9Mrrc7ttHC6z8mb7K90t99vwJaYuQeHaSwoH0m2gb0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2004, 52, 'https://1.bp.blogspot.com/Gr46wq-dbcjg4QNchzR8vv8Nixf0haA3akOvmkh-ivKAOLgwNMS-FkxqZ_5fdjcxM0A3pZqXWbjBX6AXQBwgrB8t2pkgW0mp0FvsVx5eXhsIjdFQ4-pdc6GaRXDcOY76EI5BVdsD9vg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2005, 52, 'https://1.bp.blogspot.com/J9DMKc9r6eLv3kt8qBm5OA98TDSUZCWSOkL6zGuQcL5YV_bZgUtOxJyz_Ad3Yh45zNMkCJDkbz0-jNymmg_jXQ-NPoLJUUHQlrXEcEmDZKHR-2YRRY1XhrA8vfLXl_7htv0KhgznRYY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2006, 52, 'https://1.bp.blogspot.com/kABJ36t2XY4xhdBhxfqiTjR7W18iyEqFPvHgAob_SK4Vew2wEQjffvRn2caQSoB59bxz2_E3WsczCNt3bGyy-QDH_40KLwCtEmQ_o_LALGlX0swG88owDWbgGjFvL3zGgHOO0E9BJ1k=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2007, 52, 'https://1.bp.blogspot.com/Gj-Ogba6eWkDfGzZBSHDyEb8-LyBsfcCEGzcrliUyypG2PLFaUJEoNsbo-eruHW8Bh6y6XwhX01k2fBQiypZdmncbWnr6gvAMbAsUDWuyZhVUaiYp9-SmnbXoW4QWaFU5avqc2TF5TY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2008, 52, 'https://1.bp.blogspot.com/kuJzD9SM8swrPQ18DHLi6QvTfvcPW3nBYyMgcS8hcz7z36pvPVFXZLO4eXbq7fK0DlRnyglcMrJhL5dNlVGlSMzK2min3n_qqeDcW7Ot8FAE1noUe52yArldmeInpMaGzng_xCcFX_A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2009, 52, 'https://1.bp.blogspot.com/5yiXJPbKv33hOnu4IaGxhRhPGp2YmsCBe6L5_NwWX6ld-G9IM8jfHW_jD8SzAn1pj8xYlROXKJxvK1aoUGjBG7zbsvvVky1GhTEl524GQlw5X0QT11yL5TfOc7YrbO4Y6T06yvWGjqs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2010, 52, 'https://1.bp.blogspot.com/3pS8beyo6Yea65QlsekIlYepGwtqA5V8Jbm8eRsgHvqw4VXMTz7kqKC8dhUfAE4575pv-Te0kEnZuntyTdrjMO9W3v5Ne4k4aeCBPar-CcLUbVidSyoAq1pi2MGyTjY5GLqP-OnDJ3I=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2011, 52, 'https://1.bp.blogspot.com/ba4jF_X78_eOU_YXzafMcJHcf1Y2QHzOvUVjsSzrdfuTi4SiNyaE_WpIGghTn59IElnEHsfGg54Lm1TOEv8khrLicpidROm3UtA3xXwukwQ7afUB6urHyAb4NuC-pHZInLR_5WNPhRk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2012, 52, 'https://1.bp.blogspot.com/0Byk_TuHgrrZPAu6ganbYF_gdXgFbO0T9aexVTHsU6J5Qf7vSkQQKzpH7ecV1C5ggR2l7LCgOL6fMiZLDgIXETkrazk_mabUprpJmAI4JbSAP5dq7PxYHQXhrhR25ZZjCnQpjKDExLs=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2013, 53, 'https://1.bp.blogspot.com/dbU12j6ZBF_2fFnVRC70V4oIreY8LHx_t9sep_txIS_x5FgclL7KcKL0ExfdD2bUeDZvW4uag6H2jpuQjSm9VqnUMqrUL7u6GU1mvM1Ej528KI3r1y9mCvcHvDlRw-8sPBygd9V00So=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2014, 53, 'https://1.bp.blogspot.com/2Q_hsuqJ_UFDhcZ2vYpXxTE4FEdJvwbS5d3ZDH4XjeZ5b7o6Qv18koybtATo_MSCz9-ZJ-GTHwbUH7pOC9Yvugd7cED7bJZE0wSb_WeEhCzr3W81qMQS3ZX3x0BtkIHf1KKNVUN__u4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2015, 53, 'https://1.bp.blogspot.com/lJ4RiJfR3Zpo3QwqPKRyG9POMbhlPRMuclgyD7Hj5h5vXhSoH-IMl-YyHGjgy2ffhsgDwq5PbLQA95a2QgY2HbPDvM8laG-FBmxATFHEbWIvZou4Ud_Clz0dXaWbCTshjaD25NBgLaI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2016, 53, 'https://1.bp.blogspot.com/-Cj0nycHbBLkTv3wJIMBYcD5gU989Jw-SpxjDmGw3cSCka7Ah68L5SkAvkchYUGnL3Mz7rUmS084YbBT82otY09sOnvqauKpLr1nZIZW_pmxehy2HYdvfdNh9UH_jSNZbSTYl0J_nJE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2017, 53, 'https://1.bp.blogspot.com/pXuOjfTBTNeutwC6kDntIG45qGPa1xx_DJQtss6LoTur21T7wN2nXqqXnvll5hE8RzBFKStKnQloSR4WdYeGiqV9h45PyaTtsoZTWrPuXQAer4hoEQEmtvNu_0qcvw3CsMQyc4QCyGo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2018, 53, 'https://1.bp.blogspot.com/qV5n-aJ44yaHXF-Z7Hm-aXgNxtsUUqq1xE5c5cIdAQW5RmSHdWp8Cijk2VtozuodFLGKuKvPwTcZptVsa8Xpw4kvYdpHIjEarU6j8v7ptWz5qcpv3awjXwNBmKmyiUGWqOZWS-DFeJc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2019, 53, 'https://1.bp.blogspot.com/X95H4CTwmB8bOUHsmOsW_99XUy9KiY9uYW7OiUdcTSmWr5mUWu4pjDQgU46sVqHI72Z_LRDGRM6AJ_2ozA7mWKaz48nESLgmib1RmE1peMml7JEVEaN8FKITgWy1rm6-lvGygBIzXVY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2020, 53, 'https://1.bp.blogspot.com/eW5I_N76qrYUY5KV8HQixU9GBlchphP_DdQLlpQ_e7biNmvSMQ3bNNK_oyD-1rwRCos3J72cKB2sDfcAqgM6e5OTIr7DSOP4jOSOcVd9G1b3OKeViJKsBZqTWPvpPyRZUDfvgGMX39A=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2021, 53, 'https://1.bp.blogspot.com/xfG-p-2PWHlYUQxLLB83GtiL6_JuUPqBW2iFUOicVQ4a9-YkN_o7W5Sisgp0-yElKsZLzsKl7KIg0zLa2dE1hjqmNO95JE82XLrvft1I4au4Mj9YqbFfbFq7_I1C1Zc2USjjZOr_lFA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2022, 53, 'https://1.bp.blogspot.com/sIgQm3nTHq1UwpFso8ee-OhsW05PbsXx-n7BQDU8_cpwse6w5iv_4b3LVjAe0l99-OGEEH-nWF2KD3wo4fjdbCs0sS3OkHzCk8rvQ4ZkZeRNXx0Bp9w3InAAq9A-p_KUzdXE4bsFsgQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2023, 53, 'https://1.bp.blogspot.com/S9pyryMxrpYIjtz3EMScsZGQQYjdIII4JFEJe-Q2EbZwxmlNcOpBxmjP2Rn1WCArv6jlND3I-zT1Jhxw1lLcgiCujxSVyHNRL5kTb5nVA_vyJhkr6o93A0LtNilauPKui83bYYcHFE0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2024, 53, 'https://1.bp.blogspot.com/5gALQXL_ClFltJGTX9Y9EfdWgd8b4O_XtBtNtWxiLrZ_v6Sty5lJ5SHRLfA6sKGUpC3EVvlvMmEi58_BQ3pzO43UQbyNWX5ePbldkDL2lbAPx5-I4OjSeYqW0Y0EzzLHh4DjMvVEOTc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2025, 53, 'https://1.bp.blogspot.com/3JbMagrC_JbPfH7LZpnJYqYYQBmwVy1ueswgFFySQJWqgxB31hEMf8ujq6Ns6ZHybpkh7BOsy3jcAmas_i_-9uQOLF6JmDBX1fSxihr2UCq0LS5AnGE-6GrKh0fHG56QBGRnL2lxEcY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2026, 53, 'https://1.bp.blogspot.com/Gdzy5AE7N8JGhJTCDWguiKg-XH7uSBceG3tZURYbVsbL5OVplT7zciFBi2FxM5vQdIiEX8iGrPBxuiWW8sgcsuRG7rD63r5lUzlar2zv1IOm7ilj4J4x_tXcH7Npq-WfZRglOJdWfIA=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2027, 53, 'https://1.bp.blogspot.com/Sso9kYkI6FsC4ZIydFZvMeB96UCV2sfzEyVAnqzla2Vfvox1isTzekyApKCk-PhmdFjGcTPlqk4YxTRLB9PvvBwVwmG-NYH54lac8wdAFvpRAIIdLDT6oZZaQaTiU9u_BkVV0bLCFcM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2028, 53, 'https://1.bp.blogspot.com/mGYq38TyXRFjCC68FOp8FQAy_7Zxcf3Yz5JesQKxFwmPY-E2XQV_yySAP_Tkucjol7PE9v9NAcXo-saYtyr73-scF4RYnrG7WD3oCm7YfGirIJOLTMq2N-8AMQNCVIbfCX1IevTCJ5o=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2029, 53, 'https://1.bp.blogspot.com/FaZAEDE0vsL5vmATrgvgk8WxJzoDcxCx0cXApe73DSzWSIa7HBY6kmZBDL9bUHhoS_-ZwjBpj1YCc3oUhGSmrMd3qSvfHz_4HrOTubyRh9CxqBH3g2mKG39zcd3BlH08Cg-40OjachY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2030, 53, 'https://1.bp.blogspot.com/jZx-nitHGinnZ2OKYMDoadTP3KVvu5nPf6Y0iBbiLm-0iWpq9f59rtY5B7sxKuCWmbUQYSThdysMEqhPqRhQwo_pXt2aBGdjYsK_iGiulw45AyI2ExEDP2rDUFESNcqm2BPVkTJ_7TQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2031, 54, 'https://1.bp.blogspot.com/IxEsPkf2hQWaB0ktu4pv4qpdocylz9-jFzZ87Zf3OUFBQct5z_DBL5RK-mdMys_cIAkAap346hDW8UVAsdEf6nGZTLw6nWJ8Ln9_WVCsmRuYHLW9KNwLiloZCzLsrFtN68Ux-Yg2gOk=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2032, 54, 'https://1.bp.blogspot.com/VfwxNy88nVE02C81JGgbCvp-jjhmwbQEscjcJ88vrolRqymt12u_HT7pEfXMcBvwJxqyQD80-qH8yQcpOlUfpcOFTToC3DWVBlOF4vkTTukV9WwvaS0n5xXH8TgG9dvLdg0OchrEsWY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2033, 54, 'https://1.bp.blogspot.com/Rce9LYfuj69xalttpQZsR8PdCuJ71W0HX3Ur5cBK_e3lS_9ZTPesSLvkoLXDrm8GtPF9SpBWS-O899SENWRKHTq5hBKdPwFZTo3CkahNjBl2hlZH3_HajdMED1CV2spgXUYiReGBfmI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2034, 54, 'https://1.bp.blogspot.com/m2gJc1eBqGtXavdA6Qc3M8DbkbzD3bDwxirpTQdyRxpOOoH-gzmTFnjrBYsGnKprqvfS3KcLMO2NMrV-MvhOtsMzWLoVGyTSEXlhKPVmyejNPpEdShiKJAflL3mSeh-7NXBHavzVyl0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2035, 54, 'https://1.bp.blogspot.com/IKswlqOiNUlTJrKYV-bS8K3WvC0pBr2zMXUSKMBFgAz30cevB9VpiaUXUHdS_53s_V1jwy7ddNUKtA5bEv8TuG8YYOiMydwTqRJhxoWH9PIjw_TNN-PD47K3OvYc4Lwi6dij8gDv2B8=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2036, 54, 'https://1.bp.blogspot.com/WwFHVDAoeAgHEKlNdo0epuCCfSzV-gSgolUl70ukYB0DcSMALxf4ufH2m_2YRs_maqc7BBokBoRNiAkkWkLFE-P3UpPvFF1NMcDyOjveBlFuGyjgXhsZjQ5WvkPorWAS936wFNVZydY=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2037, 54, 'https://1.bp.blogspot.com/3AiKgK2n832Cmi3i2Ff36RfVMYqDOO4qKkkW-m32kqqnnEBjhS3poC7MUJQD-4H2L9MEzQQl-d7Cs03SyJVl3tHPw0Umh47wlbKitKjTXmPgONemM3DqZe32hIJQ9VS-LAIUYLccIkQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2038, 54, 'https://1.bp.blogspot.com/a5N6rR31osSOZa_Co3k2g91ibUZFPC9P7XTpHAf1KNi_Ekj3lDTnjc1iC2Bw_9pMRct7d6oVQ_w9A_hswhS_5wMhxxu-pL1tDgIulr8GRO4zj0lERX6zqZdafr2K6GHe4F-q-MgPqjU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2039, 54, 'https://1.bp.blogspot.com/IFTOYjaW8ZHPS9hmjt9VpvbE7jqngHpudgXnuejYpOrE-ljdVqJfebjVguxlGL5qdKjq0PO4re0NoI4_JCt6Ov0DLEc2Akgp-9AO0Nf2julSbWLZgvnYUrEZh8C96YB0Z2crJqBd_K0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2040, 54, 'https://1.bp.blogspot.com/n4WXXS4D_EOaFCW2p4ZwAlkXycXxP3rN1DZ1UYK8rjHrS6Hahlsb5_n_JKplItGAbFaea2ttwVvrfcQszwse_OIa-D_LyouFENB8fIlOTN_xaaG6Qafi6mbL9zEJowTbyfD5t61LSN0=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2041, 54, 'https://1.bp.blogspot.com/l7rM_xvB3y4SqvUb6uSVbrtOzMGZAW1p0VIb3LVr0s9IVzOeqvkwPXbX-7LbjTMeu8z4z1oCcWZrQdRAtXHLx2Z4PumVo3X-jtwNGe5fj0-Q7g2Y1E8alQ8oA3Yyoy881aF1TbSRtYM=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2042, 54, 'https://1.bp.blogspot.com/7D0n0H-hRedZPclZh04IwkTsnJ1fptJ1dNl6EvwdV41K9DxbwGGOycsEtHSbbDh_S-oWFJ5O02dyOifZ2Y9hBOFHxx-GjyGwvseu0pH-S71GqK5Cu5hiL5qbGMXLAFg5bWI4Dz4M1jc=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2043, 54, 'https://1.bp.blogspot.com/NB4Q_ALcH3X0r1_oypk5pL5_R231Go3QEWg99w4P2V1y5kq06TDUAHAAD5MaSFVbFSh6Q_6cY9EBiwnlG9fEt5mFu56j4GhY7rF_TnzRvkxNsclbTRQIexOeSfuT7w8Sb4oIcK_FuSI=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2044, 54, 'https://1.bp.blogspot.com/lk1NkJDKkkB0He72-lDrETSKOTkSBIwnwFil1sBzjRRB48E6E9WDyooWluagHG6fT2oqaLVlyh9DKlQg5MKuxZm67bdVLUY2uCS1tyj5uhqMKkexMmekkTuUe2qNdAO8aVSqLR36ySQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2045, 54, 'https://1.bp.blogspot.com/RYSyznqt6o13wEvDvsTYm8g4ymhd-LKXxhuLeq9u14-g_uzm6VH36hPUCC_DS5GJcVB4g5hhlxKmseNluY2HYu5QpoirUGJghDKONOs2Ux2gp1CkPsy7zks6d0VVqkbw-lpq7_xLNYQ=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2046, 54, 'https://1.bp.blogspot.com/HcR6Q3HRbqjREduU8oQbwVcUSVVMXfR4fhF-STW9HbzfurH5tbhDP0tEriZWztiIuQpUhsNN5SJXtl_Jje8cAGACrRDYb-lk0wMmo1hX38TBfX8RexG7aEZesNzL3w4QOo-yMbyvBuU=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2047, 54, 'https://1.bp.blogspot.com/F9nIaU-afxTZaaL92TBXP6YA57gV2wlR8PhXG7dWN3aWKRKpS62g8dWiFVFatWJX62jNGx9EHPOUQ56RR4vcpjEqCHsn6u1J1M_UGWnzBxGfMJO1GEdCCAmDxTBMOqukBn6UaqSj3Wo=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2048, 54, 'https://1.bp.blogspot.com/15X5ZXsTLoYTAmy-PrYgP90KZb7rDdxMeF4D8AlnrOBpp4CwmzAUVcqfnG8klpzfs1nwPaGLK49McJ4YpkwQ9-eK5QhaaAod7hWt0ZbLJdE0yb8B-fN08i-eY5J9AS0eYRJC9cIPKcg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2049, 54, 'https://1.bp.blogspot.com/O9R31ock9EkiHxNnH2ZHSG-1ALLLzYy3-ZtBHO5FX6kZTEv3ezLq8YQ9z9a4CPPjMcFVcC6L1-QyPoGwbyC1G4XP6pX7nmNk4eZOFXTez54oOd6IfB6vqG1caEgBBVlPC5Q99axSCa4=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2050, 54, 'https://1.bp.blogspot.com/hh-csNItFpy8d7x_4mZVJgTHy-gnxyTDr290OdHiNbSL7pvjINzZ8FEKHu8xBKFF9YZPr0mCKTCnoXVD18CBu08xcu6oSXdMGntJ0YRMA7xa7G7aohonn5wM3_UgW0NHFXrqG56gjWg=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2051, 54, 'https://1.bp.blogspot.com/Oei0meZW5F20GF_YVFgiKD1oFx-OkiKe3AT_RbxNHta5qAhsS3pNa_896cuYHBp0qd8tZJ8bOnSPNM1A9E-G9L4U-nVeDfkcTgICGd9ETqBo59kZ2WXlbaFacwXKOpocwBMbAWWmzHE=s0?imgmax=0');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2052, 54, 'https://1.bp.blogspot.com/bEPb6yWwUbhRifY-YBJQZ5o2s6W4Rwa382pzxW7vOxv-GUPCPkJTeC6FIeP1aoaRI0st2dzUW5F-ZTci8QlDKzzUm82gYsrDtdlA6nvtpGCb3I2gY1-1IJsthO84bBr1s4iN_4tQ5x0=s0?imgmax=0');");

        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2053, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/1.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2054, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2055, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2056, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2057, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2058, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2059, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2060, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2061, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2062, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2063, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2064, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2065, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2066, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2067, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2068, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2069, 55, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-1-heroine-girl-and-background-boy/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2070, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2071, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2072, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2073, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2074, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2075, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2076, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2077, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2078, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2079, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2080, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2081, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2082, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2083, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2084, 56, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-2-a-bad-temper-and-the-top-of-a-lap/16.jpg');");

        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2085, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2086, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2087, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2088, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2089, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2090, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2091, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2092, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2093, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2094, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2095, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2096, 57, 'https://cdn.bakihanma.com/file/Zolyvmanga/kubo-san-doesn-t-leave-me-be-a-mob/chapter-3-the-right-to-answer-and-a-busybody/13.jpg');");

        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2097, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2098, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2099, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2100, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2101, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2102, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2103, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2104, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2105, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2106, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2107, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2108, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2109, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2110, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2111, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2112, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2113, 58, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/f19f8e897023dc7e7159893c49b9be75/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2114, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2115, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2116, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2117, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2118, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2119, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2120, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2121, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2122, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2123, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2124, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2125, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2126, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2127, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2128, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2129, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2130, 59, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/2894db96f9ddf21dd31a7d52d30ebb24/18.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2131, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/2.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2132, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/3.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2133, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/4.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2134, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/5.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2135, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/6.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2136, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/7.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2137, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/8.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2138, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/9.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2139, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/10.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2140, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/11.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2141, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/12.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2142, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/13.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2143, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/14.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2144, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/15.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2145, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/16.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2146, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/17.jpg');");
        db.execSQL("INSERT INTO noidungchapter (id, idchapter, linkanh) VALUES (2147, 60, 'https://cdn.mangaclash.com/manga_5fdf114caf71c/817e2026582a9eeaebdae9d775d25878/18.jpg');");


        // Bình luận
        db.execSQL("CREATE TABLE if not exists binhluan (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                       NOT NULL,\n" +
                "    idchapter  INTEGER REFERENCES chapter (id) \n" +
                "                       NOT NULL,\n" +
                "    idtaikhoan INTEGER REFERENCES taikhoan (id) \n" +
                "                       NOT NULL,\n" +
                "    noidung    TEXT    NOT NULL,\n" +
                "    ngaydang   DATE    DEFAULT (date('now') ) \n" +
                "                       NOT NULL,\n" +
                "    trangthai  INTEGER DEFAULT (1) \n" +
                "                       NOT NULL\n" +
                ");");

        //insert binhluan
        db.execSQL("INSERT INTO binhluan (id, idchapter, idtaikhoan, noidung, ngaydang, trangthai) VALUES (1, 35, 2, 'truyện hay quá bạn ơi!', '2021-11-21', 1);");
        db.execSQL("INSERT INTO binhluan (id, idchapter, idtaikhoan, noidung, ngaydang, trangthai) VALUES (2, 35, 2, 'tuyệt vời!', '2021-11-21', 1);");
        db.execSQL("INSERT INTO binhluan (id, idchapter, idtaikhoan, noidung, ngaydang, trangthai) VALUES (3, 35, 2, 'tuyệt vời!', '2021-11-21', 1);");
        db.execSQL("INSERT INTO binhluan (id, idchapter, idtaikhoan, noidung, ngaydang, trangthai) VALUES (4, 35, 2, 'tuyệt vời!', '2021-11-21', 1);");
        db.execSQL("INSERT INTO binhluan (id, idchapter, idtaikhoan, noidung, ngaydang, trangthai) VALUES (5, 35, 2, 'a', '2021-11-21', 1);");
        db.execSQL("INSERT INTO binhluan (id, idchapter, idtaikhoan, noidung, ngaydang, trangthai) VALUES (6, 35, 2, 'b', '2021-11-21', 1);");

        //Đánh giá
        db.execSQL("CREATE TABLE if not exists danhgia (\n" +
                "    id          INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                        NOT NULL,\n" +
                "    idchapter   INTEGER REFERENCES chapter (id) \n" +
                "                        NOT NULL,\n" +
                "    idtaikhoan  INTEGER REFERENCES taikhoan (id) \n" +
                "                        NOT NULL,\n" +
                "    sosao       FLOAT   NOT NULL,\n" +
                "    ngaydanhgia DATE    DEFAULT (date('now') ) \n" +
                "                        NOT NULL\n" +
                ");\n");

        //insert danhgia
        db.execSQL("INSERT INTO danhgia (id, idchapter, idtaikhoan, sosao, ngaydanhgia) VALUES (1, 35, 2, 5.0, '2021-11-21');");
        db.execSQL("INSERT INTO danhgia (id, idchapter, idtaikhoan, sosao, ngaydanhgia) VALUES (2, 1, 3, 2.5, '2021-11-21');");
        db.execSQL("INSERT INTO danhgia (id, idchapter, idtaikhoan, sosao, ngaydanhgia) VALUES (3, 1, 4, 4.0, '2021-11-21');");
        db.execSQL("INSERT INTO danhgia (id, idchapter, idtaikhoan, sosao, ngaydanhgia) VALUES (4, 12, 1, 4.0, '2021-11-21');");
        db.execSQL("INSERT INTO danhgia (id, idchapter, idtaikhoan, sosao, ngaydanhgia) VALUES (5, 12, 2, 3.5, '2021-11-21');");
        db.execSQL("INSERT INTO danhgia (id, idchapter, idtaikhoan, sosao, ngaydanhgia) VALUES (6, 4, 3, 3.0, '2021-11-21');");
        db.execSQL("INSERT INTO danhgia (id, idchapter, idtaikhoan, sosao, ngaydanhgia) VALUES (7, 35, 1, 4.0, '2021-11-21');");

        //Điểm thưởng
        db.execSQL("CREATE TABLE if not exists diemthuong (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                       NOT NULL,\n" +
                "    idtaikhoan INTEGER REFERENCES taikhoan (id) \n" +
                "                       NOT NULL,\n" +
                "    diem       INTEGER NOT NULL,\n" +
                "    ngaynhan   DATE    NOT NULL\n" +
                "                       DEFAULT (date('now') ),\n" +
                "    thu        INTEGER NOT NULL\n" +
                "                       DEFAULT (1) \n" +
                ");\n");
        //insert diemthuong
        db.execSQL("INSERT INTO diemthuong (id, idtaikhoan, diem, ngaynhan, thu) VALUES (9, 2, 5, '2021-11-20', 1);");
        db.execSQL("INSERT INTO diemthuong (id, idtaikhoan, diem, ngaynhan, thu) VALUES (10, 1, 5, '2021-11-20', 1);");
        db.execSQL("INSERT INTO diemthuong (id, idtaikhoan, diem, ngaynhan, thu) VALUES (11, 8, 5, '2021-11-20', 1);");
        db.execSQL("INSERT INTO diemthuong (id, idtaikhoan, diem, ngaynhan, thu) VALUES (12, 2, 5, '2021-11-24', 1);");
        db.execSQL("INSERT INTO diemthuong (id, idtaikhoan, diem, ngaynhan, thu) VALUES (13, 2, 5, '2021-11-25', 2);");


        // Đổi thưởng
        db.execSQL("CREATE TABLE if not exists doithuong (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                       NOT NULL,\n" +
                "    idvatpham  INTEGER REFERENCES vatpham (id) \n" +
                "                       NOT NULL,\n" +
                "    idtaikhoan INTEGER REFERENCES taikhoan (id) \n" +
                "                       NOT NULL,\n" +
                "    ngaydoi    DATE    NOT NULL\n" +
                "                       DEFAULT (date('now') ) \n" +
                ");\n");
        //insert doithuong
        db.execSQL("INSERT INTO doithuong (id, idvatpham, idtaikhoan, ngaydoi) VALUES (1, 6, 2, '2021-11-20');");
        db.execSQL("INSERT INTO doithuong (id, idvatpham, idtaikhoan, ngaydoi) VALUES (2, 1, 2, '2021-11-20');");


        // Lịch sử đọc truyện
        db.execSQL("CREATE TABLE if not exists lichsudoctruyen (\n" +
                "    id         INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                       NOT NULL,\n" +
                "    idtaikhoan INTEGER REFERENCES taikhoan (id) \n" +
                "                       NOT NULL,\n" +
                "    idchapter  INTEGER REFERENCES chapter (id) \n" +
                "                       NOT NULL\n" +
                ");\n");
        //insert lichsudoctruyen
        db.execSQL("INSERT INTO lichsudoctruyen (id, idtaikhoan, idchapter) VALUES (1, 2, 9);");
        db.execSQL("INSERT INTO lichsudoctruyen (id, idtaikhoan, idchapter) VALUES (2, 2, 35);");


        // Thông báo
        db.execSQL("CREATE TABLE if not exists thongbao (\n" +
                "    id       INTEGER        PRIMARY KEY AUTOINCREMENT\n" +
                "                            NOT NULL,\n" +
                "    tieude   NVARCHAR (250) NOT NULL,\n" +
                "    noidung  TEXT           NOT NULL,\n" +
                "    ngaydang DATE           NOT NULL\n" +
                "                            DEFAULT (date('now') ) \n" +
                ");\n");
        //insert thongbao
        db.execSQL("INSERT INTO thongbao (id, tieude, noidung, ngaydang) VALUES (1, 'Xử lý vi phạm', 'Những tài khoản có bình luận văn tục, bôi nhọ danh dự của cá nhân, tổ chức sẽ bị khóa vĩnh viễn. Thông tin đến đọc giả.', '2021-11-19');");
        db.execSQL("INSERT INTO thongbao (id, tieude, noidung, ngaydang) VALUES (2, 'Bảo trì hệ thống', 'Hệ thống ứng dụng đọc truyện Argon Comic xin phép tạm dừng vào ngày 06-12-2021 để bảo trì. Thông tin đến đọc giả.', '2021-12-05');");


        // Thống kê
        db.execSQL("CREATE TABLE if not exists thongke (\n" +
                "    id          INTEGER PRIMARY KEY AUTOINCREMENT\n" +
                "                        NOT NULL,\n" +
                "    idtruyen    INTEGER REFERENCES truyen (id) \n" +
                "                        NOT NULL,\n" +
                "    tongluotxem INTEGER DEFAULT (0) \n" +
                "                        NOT NULL,\n" +
                "    sosaotb     FLOAT   DEFAULT (0) \n" +
                "                        NOT NULL\n" +
                ");\n");
        //insert thongke
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (1, 1, 17, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (2, 2, 3, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (3, 3, 2, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (4, 4, 2, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (5, 5, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (6, 6, 1, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (7, 7, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (8, 8, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (9, 9, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (10, 10, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (11, 11, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (12, 12, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (13, 13, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (14, 14, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (15, 15, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (16, 16, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (17, 17, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (18, 18, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (19, 19, 0, 0.0);");
        db.execSQL("INSERT INTO thongke (id, idtruyen, tongluotxem, sosaotb) VALUES (20, 20, 0, 0.0);");


        // Vật phẩm
        db.execSQL("CREATE TABLE if not exists vatpham (\n" +
                "    id         INTEGER        PRIMARY KEY AUTOINCREMENT\n" +
                "                              NOT NULL,\n" +
                "    tenvatpham NVARCHAR (250) NOT NULL,\n" +
                "    diem       INTEGER        NOT NULL,\n" +
                "    linkanh    TEXT           NOT NULL\n" +
                ");");
        //insert vatpham
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (1, 'Avatar Girl 1', 100, 'https://i.pinimg.com/564x/be/b2/34/beb234d9273e8c029659b37b94992e45.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (2, 'Avatar Girl 2', 100, 'https://i.pinimg.com/736x/08/41/6e/08416e44638f0b21e496332715b11f97.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (3, 'Avatar Girl 3', 100, 'https://i.pinimg.com/564x/fa/bd/d9/fabdd9a477995015fbd962d24aa11f61.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (4, 'Avatar Girl 4', 100, 'https://i.pinimg.com/564x/16/cf/11/16cf11c37520040742aee83aeebe395c.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (5, 'Avatar Girl 5', 100, 'https://i.pinimg.com/564x/f6/8d/fc/f68dfce8969061c60bf35c3a92632ba6.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (6, 'Avatar Boy 1', 100, 'https://i.pinimg.com/236x/06/2f/8f/062f8f8d0f731a00e565a45606339762.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (7, 'Avatar Boy 2', 100, 'https://i.pinimg.com/236x/27/a5/26/27a526af665a5a54d7ab27cfcaaf3c76.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (8, 'Avatar Boy 3', 100, 'https://i.pinimg.com/236x/67/e7/d8/67e7d8c9d7b38db375ecd960dfd6cb6d.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (9, 'Avatar Boy 4', 100, 'https://i.pinimg.com/236x/7c/b7/a9/7cb7a9baf775c8895d7fd7a95771d63f.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (10, 'Avatar Boy 5', 100, 'https://i.pinimg.com/236x/86/12/13/86121300cc7a7a3145b71dca9ebc77cd.jpg');");
        db.execSQL("INSERT INTO vatpham (id, tenvatpham, diem, linkanh) VALUES (11, 'Avatar Boy 6', 100, 'https://i.pinimg.com/236x/bb/d8/b6/bbd8b61fec49b2d9b32aebceb79dbe77.jpg');");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists taikhoan");
        db.execSQL("drop table if exists truyen");
        db.execSQL("drop table if exists chapter");
        db.execSQL("drop table if exists noidungchapter");
        onCreate(db);
    }

    //Tài Khoản
    public Boolean insertNewTaiKhoan(String email,String matkhau,String ten, String dienthoai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("matkhau", matkhau);
        values.put("hoten",ten);
        values.put("dienthoai",dienthoai);

        long result= db.insert("taikhoan",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean insertTaikhoan(String email, String matkhau){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("email", email);
        values.put("matkhau", matkhau);

        long result= db.insert("taikhoan",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean ckeckEmail(String email) {
        SQLiteDatabase db =this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from taikhoan where email=?", new String[] {email});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean checkEmailMatkhau(String email, String matkhau) {
        SQLiteDatabase db= this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from taikhoan where email=? and matkhau=?", new String[] {email,matkhau});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public ArrayList<Account> getListTaiKhoan(){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<Account> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from taikhoan where loaitk not in (1)",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Account account = new Account(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getString(7));
            list.add(account);
            cursor.moveToNext();
        }
        return list;
    }
    public Account getTaiKhoan(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from taikhoan where email=?", new String[] {email});
        if(cursor != null)
            cursor.moveToFirst();
        Account account = new Account(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getString(7));
        return account;
    }

    public Account getTaiKhoanId(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from taikhoan where id=?", new String[] {""+id});
        if(cursor != null)
            cursor.moveToFirst();
        Account account = new Account(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4),cursor.getInt(5),cursor.getInt(6),cursor.getString(7));
        return account;
    }

    public Boolean updateMK(String email, String matkhau){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("matkhau", matkhau);
        long kq=db.update("taikhoan",values,"email=?",new String[]{email});
        if(kq==-1) return false;
        else
            return true;
    }

    public Boolean updateDiemThuong(Account account, int diem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("diemthuong", account.getRewardPoint()+diem);
        long kq=db.update("taikhoan",values,"email=?",new String[]{account.getEmail()});
        if(kq==-1) return false;
        else
            return true;
    }

    public Boolean checkLinkAnh (Account account, String linkanh){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from taikhoan where linkanh=? and id=?", new String[] {linkanh,""+ account.getId()});
        cursor.moveToFirst();
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }

    public Boolean updateLinkAnh(Account account, String linkanh){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("linkanh", linkanh);
        long kq=db.update("taikhoan",values,"id=?",new String[]{""+ account.getId()});
        if(kq==-1) return false;
        else
            return true;
    }

    public String getEmail(int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        String email;
        Cursor cursor = db.rawQuery("select email from taikhoan where id=?", new String[] {""+idtaikhoan});
        cursor.moveToFirst();
        email=cursor.getString(0);
        return email;
    }

    public Boolean updateTrangThai(int idtaikhoan,int trangthai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("loaitk",trangthai );
        long kq=db.update("taikhoan",values,"id=?",new String[]{""+idtaikhoan});
        if(kq==-1) return false;
        else
            return true;
    }

    public int checkTrangThai(String email){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select loaitk from taikhoan where email=?", new String[] {email});
        cursor.moveToFirst();
        int loaitk=cursor.getInt(0);
        if (loaitk==2)
            return 0;
        else if(loaitk==3){ return 2;}
        else return 1;
    }

    public Boolean updateThongTin(String ten, String dienthoai,int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("hoten", ten);
        values.put("dienthoai",dienthoai);

        long kq=db.update("taikhoan",values,"id=?",new String[]{""+idtaikhoan});
        if(kq==-1) return false;
        else
            return true;
    }

    //Truyện
    public int getIdTruyenByIdChapter(int idchapter){
        SQLiteDatabase db = this.getWritableDatabase();
        int id=0;
        Cursor cursor = db.rawQuery("Select idtruyen from chapter where id=?",new String[] {""+idchapter});
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            id=cursor.getInt(0);
        }
        return id;
    }

    public Boolean updateTruyen(int id,String tentruyen,String tacgia,String mota, String theloai,String linkanh,int trangthai,String key_search){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tentruyen", tentruyen);
        values.put("tacgia", tacgia);
        values.put("mota", mota);
        values.put("theloai", theloai);
        values.put("linkanh", linkanh);
        values.put("trangthai", trangthai);
        values.put("key_search", key_search);
        long kq=db.update("truyen",values,"id=?",new String[]{""+id});
        if(kq==-1) return false;
        else
            return true;
    }

    public Boolean deleteTruyen(int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();

        long kq=db.delete("truyen","id=?",new String[] {""+idtruyen});
        if(kq==-1) return false;
        else
            return true;
    }

    public ArrayList<Story> getListTruyen(){
        ArrayList<Story> truyenlist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from truyen",null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            Story story = new Story(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getInt(6), cursor.getString(7));
            truyenlist.add(story);
            cursor.moveToNext();
        }
        return truyenlist;
    }

    public ArrayList<String> listTenTruyen(){
        ArrayList<String> listtentruyen = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select tentruyen from truyen",null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            String tentruyen=cursor.getString(0);
            listtentruyen.add(tentruyen);
            cursor.moveToNext();
        }
        return listtentruyen;
    }

    public ArrayList<Story> getTruyen(String lenhSQL) {
        ArrayList<Story> truyenlist = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(lenhSQL,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            Story story = new Story(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getInt(6), cursor.getString(7));
            truyenlist.add(story);
            cursor.moveToNext();
        }
        return truyenlist;
    }

    public Story getTruyenById(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from truyen where id=?",new String[] {""+id});
        cursor.moveToFirst();
        Story story = new Story(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5), cursor.getInt(6), cursor.getString(7));
        return story;
    }

    public Boolean insertTruyen(String tentruyen, String tacgia, String mota, String theloai, String linkanh, String key_search){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tentruyen", tentruyen);
        values.put("tacgia", tacgia);
        values.put("mota",mota);
        values.put("theloai",theloai);
        values.put("linkanh",linkanh);
        values.put("key_search",key_search);

        long result= db.insert("truyen",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    //Chapter
    public Boolean updateChapter(int id,String tenchapter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenchapter", tenchapter);
        long kq=db.update("chapter",values,"id=?",new String[]{""+id});
        if(kq==-1) return false;
        else
            return true;
    }
    public Boolean insertChapter(String tenchapter,int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tenchapter", tenchapter);
        values.put("idtruyen", idtruyen);

        long result= db.insert("chapter",null,values);
        if(result==-1) return false;
        else
            return true;
    }
    public ArrayList<String> listTenChapter(){
        ArrayList<String> listtenchapter = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select tenchapter from chapter",null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            String tentruyen=cursor.getString(0);
            listtenchapter.add(tentruyen);
            cursor.moveToNext();
        }
        return listtenchapter;
    }
    public Boolean deleteChapter(int idchapter){
        SQLiteDatabase db = this.getWritableDatabase();

        long kq=db.delete("chapter","id=?",new String[] {""+idchapter});
        if(kq==-1) return false;
        else
            return true;
    }
    public ArrayList<Chapter> getChapter(String lenhSQL) {
        ArrayList<Chapter> chapters = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(lenhSQL,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            Chapter chapter = new Chapter(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getFloat(5));
            chapters.add(chapter);
            cursor.moveToNext();
        }
        return chapters;
    }

    public int getSumChapter(int id_truyen){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select count(*) from chapter where idtruyen=? ",new String[] {""+id_truyen});
        cursor.moveToFirst();
        int kq=cursor.getInt(0);
        return kq;
    }

    public Boolean updateLuotXemChapter(Chapter chapter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("soluotxem", chapter.getSoluotxem()+1);

        long kq=db.update("chapter",values,"id=?",new String[]{String.valueOf(chapter.getId())});
        if(kq==-1) return false;
        else
            return true;
    }

    public int getMinIdChapter(int id_truyen){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select id from chapter where idtruyen=? and tenchapter='Chapter 1' ",new String[] {""+id_truyen});
        cursor.moveToFirst();
        int kq=cursor.getInt(0);
        return kq;
    }

    public int getMaxIdChapter(int id_truyen){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select id from chapter where idtruyen=? order by id desc limit 1",new String[] {""+id_truyen});
        cursor.moveToFirst();
        int kq=cursor.getInt(0);
        return kq;
    }

    public Chapter getOneChapter(int idchapter){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select * from chapter where id=? ",new String[] {""+idchapter});
        cursor.moveToFirst();
        Chapter chapter=new Chapter(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getInt(4),cursor.getFloat(5));
        return chapter;
    }

    public String getTenChapterNew(int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select tenchapter from chapter where idtruyen=? order by id desc limit 1",new String[]{""+idtruyen});
        cursor.moveToFirst();
        String tenchapter=cursor.getString(0);
        return tenchapter;
    }

    public int getIdTruyen(int idchapter){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select idtruyen from chapter where id=?",new String[] {""+idchapter});
        cursor.moveToFirst();
        int idtruyen=cursor.getInt(0);

        return idtruyen;
    }

    public String getTenChapter(int idchapter){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select tenchapter from chapter where id=?",new String[] {""+idchapter});
        cursor.moveToFirst();
        String tenchapter=cursor.getString(0);

        return tenchapter;
    }

    //Nội dung Chapter
    public Boolean insertNDChapter(String linkanh,int idchapter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("linkanh", linkanh);
        values.put("idchapter", idchapter);

        long result= db.insert("noidungchapter",null,values);
        if(result==-1) return false;
        else
            return true;
    }
    public ArrayList<String> getLinkAnhNDC(int idchapter){
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<String> list=new ArrayList<>();
        Cursor cursor = db.rawQuery("select linkanh from noidungchapter where idchapter=?",new String[] {""+idchapter});
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            String linkanh=cursor.getString(0);
            list.add(linkanh);
            cursor.moveToNext();
        }
        return list;
    }

    public Boolean deleteNoiDungChapter(int idndc){
        SQLiteDatabase db = this.getWritableDatabase();

        long kq=db.delete("noidungchapter","id=?",new String[] {""+idndc});
        if(kq==-1) return false;
        else
            return true;
    }

    public ArrayList<ContentOfChapter> getNoiDungChapter(String lenhSQL) {
        ArrayList<ContentOfChapter> contentOfChapters = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(lenhSQL,null);
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            ContentOfChapter contentOfChapter = new ContentOfChapter(cursor.getInt(0),cursor.getInt(1),cursor.getString(2));
            contentOfChapters.add(contentOfChapter);
            cursor.moveToNext();
        }
        return contentOfChapters;
    }

    //Thể loại
    public ArrayList<String> getTheLoai(){
        ArrayList<String> listtheloai = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select distinct theloai from truyen order by theloai",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            String theloai= new String(cursor.getString(0));
            listtheloai.add(theloai);
            cursor.moveToNext();
        }
        return listtheloai;
    }


    //Truyện (new, view,vote)
    public ArrayList<ClassifyStory> getListPLTruyen(String lenhSQLite){
        ArrayList<ClassifyStory> listtruyen=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(lenhSQLite,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            ClassifyStory classifyStory = new ClassifyStory(cursor.getInt(0),cursor.getInt(1),cursor.getFloat(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            listtruyen.add(classifyStory);
            cursor.moveToNext();
        }
        return listtruyen;
    }

    public ArrayList<ModelSearch> getListTimKiem(String lenhSQLite){
        ArrayList<ModelSearch> listtimkiem=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(lenhSQLite,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            ModelSearch timKiem = new ModelSearch(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getFloat(3),cursor.getString(4),cursor.getString(5),cursor.getString(6));
            listtimkiem.add(timKiem);
            cursor.moveToNext();
        }
        return listtimkiem;
    }

    public Story getOneTruyen(Chapter chapter){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from truyen where id=?",new String[] {""+chapter.getIdtruyen()});
        cursor.moveToFirst();
        Story story =new Story(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6),cursor.getString(7));
        return story;
    }

    //Điểm thưởng
    public ArrayList<RewardPoints> getDiemThuong(int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<RewardPoints> list= new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from diemthuong where idtaikhoan=? ",new String[] {""+idtaikhoan});
        cursor.moveToFirst();

        while (cursor.isAfterLast() == false){
            RewardPoints rewardPoints = new RewardPoints(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getInt(4));
            list.add(rewardPoints);
            cursor.moveToNext();
        }
        return list;
    }

    public int getThu(Account account){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select thu from diemthuong where idtaikhoan=? and ngaynhan=date('now','-1 day')",new String[] {""+ account.getId()});
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }

    public int getThuHienTai(Account account){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select thu from diemthuong where idtaikhoan=? and ngaynhan=date('now')",new String[] {""+ account.getId()});
        cursor.moveToFirst();
        if (cursor.getCount()>0){
            return cursor.getInt(0);
        } else {
            return 0;
        }
    }

    public Boolean checkDiemDanh(Account account){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("select id from diemthuong where idtaikhoan=? and ngaynhan=date('now')",new String[] {""+ account.getId()});
        if (cursor.getCount()>0){
            return true;
        } else {
            return false;
        }
    }

    public Boolean  insertDiemThuong(int idtaikhoan, int diem, int thu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idtaikhoan", idtaikhoan);
        values.put("diem", diem);
        values.put("thu",thu);

        long result= db.insert("diemthuong",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    public int getTongDiemDanh(int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        int tongdiemdanh=0;
        Cursor cursor=db.rawQuery("select count(*) from diemthuong where idtaikhoan=?",new String[] {""+idtaikhoan});
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            tongdiemdanh=cursor.getInt(0);
        }
        return tongdiemdanh;
    }

    //Đổi thưởng
    public ArrayList<RewardExchange> getLichSuDoi(Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<RewardExchange> rewardExchanges =new ArrayList<>();

        Cursor cursor = db.rawQuery("select * from doithuong where idtaikhoan=? ",new String[] {""+ account.getId()});
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            RewardExchange rewardExchange = new RewardExchange(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3));
            rewardExchanges.add(rewardExchange);
            cursor.moveToNext();
        }
        return rewardExchanges;
    }

    public Boolean insertDoiThuong(int idvatpham,int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idtaikhoan", idtaikhoan);
        values.put("idvatpham", idvatpham);

        long result= db.insert("doithuong",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    public int checkVatPham(int id, Account account){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select id from doithuong where idvatpham=? and idtaikhoan=?",new String[] {""+id,""+ account.getId()});
        if (cursor.getCount()>0) {
            return 1;
        }else return 0;
    }

    public int checkDoiVatPham(int id, Account account){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select diemthuong from taikhoan where id=?",new String[] {""+ account.getId()});
        cursor.moveToFirst();
        int diemtichluy=cursor.getInt(0);

        Cursor cursor1 = db.rawQuery("select diem from vatpham where id=?",new String[] {""+id});
        cursor1.moveToFirst();
        int diemvatpham=cursor1.getInt(0);

        if((diemtichluy-diemvatpham)<0){
            return 0;
        }else{
            return 1;
        }
    }

    //Vật phẩm
    public ArrayList<Item> getVatPham(String lenhSQLite){
        ArrayList<Item> listvatpham=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(lenhSQLite,null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Item item = new Item(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getString(3));
            listvatpham.add(item);
            cursor.moveToNext();
        }
        return listvatpham;
    }

    public ArrayList<String> getVatPhamDoi(int id){
        ArrayList<String> list=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select tenvatpham,diem,linkanh from vatpham where id=?",new String[]{""+id});
        cursor.moveToFirst();
        list.add(cursor.getString(0));
        list.add(""+cursor.getInt(1));
        list.add(cursor.getString(2));
        return list;
    }

    //Lịch sử đọc truyện
    public ArrayList<ReadingHistory> getListTruyenDaDoc(int idtaikhoan){
        ArrayList<ReadingHistory> list=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor=db.rawQuery("select * from lichsudoctruyen where idtaikhoan=?",new String[]{""+idtaikhoan});
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            ReadingHistory truyen = new ReadingHistory(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2));
            list.add(truyen);
            cursor.moveToNext();
        }
        return list;
    }
    //thêm Lịch sử đọc truyện
    public Boolean insertTruyenDaDoc(int idtaikhoan, int idchapter){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idtaikhoan", idtaikhoan);
        values.put("idchapter", idchapter);

        long result= db.insert("lichsudoctruyen",null,values);
        if(result==-1) return false;
        else
            return true;
    }
    //update chapter cho truyện đã đọc dựa vào id lịch sử đọc truyện
    public Boolean updateTruyenDaDoc(int idchapter,int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idchapter", idchapter);

        long kq=db.update("lichsudoctruyen",values,"id=?",new String[]{String.valueOf(id)});
        if(kq==-1) return false;
        else
            return true;
    }
    //lấy id lịch sử đọc truyện
    public int getIdLichSuDocTruyen(int idtaikhoan,int idchapter){
        SQLiteDatabase db = this.getWritableDatabase();
        int id;
        Cursor cursor=db.rawQuery("select id from lichsudoctruyen where idtaikhoan=? and idchapter=?",new String[]{""+idtaikhoan,""+idchapter});
        cursor.moveToFirst();
        id=cursor.getInt(0);
        return id;
    }
    //Lấy idchapter của truyện đã đọc
    public int getIdChapterTruyenDaDoc(int idtaikhoan, int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();
        int id;
        Cursor cursor=db.rawQuery("select chapter.id from lichsudoctruyen inner join chapter on lichsudoctruyen.idchapter=chapter.id where chapter.idtruyen=? and lichsudoctruyen.idtaikhoan=?",new String[]{""+idtruyen,""+idtaikhoan});
        cursor.moveToFirst();
        id=cursor.getInt(0);
        return id;
    }

    //kiểm tra truyện đã đọc chưa thông qua id truyện
    public Boolean checkTruyenDaDoc(int idtaikhoan, int idtruyendangdoc){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select chapter.idtruyen from lichsudoctruyen inner join chapter on lichsudoctruyen.idchapter=chapter.id where lichsudoctruyen.idtaikhoan=? and chapter.idtruyen=?",new String[]{""+idtaikhoan,""+idtruyendangdoc});
        if (cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    //lấy thể loại được đọc giả xem nhiều nhất
    public ArrayList<String> getTheLoaiYeuThich(int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> theloai=new ArrayList<>();
        Cursor cursor=db.rawQuery("select truyen.theloai\n" +
                "from lichsudoctruyen inner join chapter on lichsudoctruyen.idchapter=chapter.id\n" +
                "inner join truyen on chapter.idtruyen=truyen.id\n" +
                "where lichsudoctruyen.idtaikhoan=?\n" +
                "group by truyen.theloai\n" +
                "order by count(*) desc ",new String[] {""+idtaikhoan});
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            while (cursor.isAfterLast() == false){
               theloai.add(cursor.getString(0));
               cursor.moveToNext();
            }
        }else {
            theloai.add("theloai");
        }
        return theloai;
    }

    public int getTongTruyenDaDoc(int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        int tongtruyendadoc=0;
        Cursor cursor=db.rawQuery("select count(*) from lichsudoctruyen where idtaikhoan=?",new String[] {""+idtaikhoan});
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            tongtruyendadoc=cursor.getInt(0);
        }
        return tongtruyendadoc;
    }

    //Bình luận
    public ArrayList<Comment> getShowBinhLuan(int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Comment> list=new ArrayList<>();

        Cursor cursor=db.rawQuery("select * from binhluan where idtaikhoan=?",new String[] {""+idtaikhoan});
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Comment binhLuan = new Comment(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
            list.add(binhLuan);
            cursor.moveToNext();
        }
        return list;
    }

    public Boolean insertBinhLuan(int idchapter,int idtaikhoan,String noidung){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idchapter", idchapter);
        values.put("idtaikhoan",idtaikhoan);
        values.put("noidung",noidung);

        long result= db.insert("binhluan",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    public ArrayList<Comment> getListBinhLuan(){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Comment> list=new ArrayList<>();

        Cursor cursor=db.rawQuery("select * from binhluan where trangthai=1 order by ngaydang desc",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Comment binhLuan = new Comment(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
            list.add(binhLuan);
            cursor.moveToNext();
        }
        return list;
    }

    public ArrayList<Comment> getListBinhLuanChapter(int idchapter){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Comment> list=new ArrayList<>();

        Cursor cursor=db.rawQuery("select * from binhluan where trangthai=1 and idchapter=? order by ngaydang desc",new String[] {""+idchapter});
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Comment binhLuan = new Comment(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
            list.add(binhLuan);
            cursor.moveToNext();
        }
        return list;
    }

    public int getTongBinhLuan(int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();

        int tongbinhluan=0;
        Cursor cursor=db.rawQuery("select count(*) from binhluan where idtaikhoan=?",new String[] {""+idtaikhoan});
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            tongbinhluan=cursor.getInt(0);
        }
        return tongbinhluan;
    }

    public int getTongBinhLuanTruyen(int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();

        int tongbinhluan=0;
        Cursor cursor=db.rawQuery("select count(*) \n" +
                "from binhluan inner join chapter on binhluan.idchapter=chapter.id\n" +
                "where chapter.idtruyen=?",new String[] {""+idtruyen});
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            tongbinhluan=cursor.getInt(0);
        }
        return tongbinhluan;
    }

    public ArrayList<Comment> getBinhLuanTruyen(int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Comment> list=new ArrayList<>();

        Cursor cursor=db.rawQuery("select * from binhluan where trangthai=1 and idchapter in (select id from chapter where idtruyen=?) order by ngaydang desc",new String[] {""+idtruyen});
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Comment binhLuan = new Comment(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
            list.add(binhLuan);
            cursor.moveToNext();
        }
        return list;
    }
    public Boolean updateTrangThaiBinhLuan(int id,int trangthai){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("trangthai",trangthai );
        long kq=db.update("binhluan",values,"id=?",new String[]{""+id});
        if(kq==-1) return false;
        else
            return true;
    }

    public Comment getThongTinBinhLuan(int id){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select * from binhluan where id=?",new String[] {""+id});
        cursor.moveToFirst();
        Comment binhLuan = new Comment(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getInt(5));
        return  binhLuan;
    }

    //Đánh giá
    public ArrayList<Evaluate> getShowDanhGia(int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Evaluate> list=new ArrayList<>();

        Cursor cursor=db.rawQuery("select * from danhgia where idtaikhoan=?",new String[] {""+idtaikhoan});
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Evaluate evaluate = new Evaluate(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getFloat(3),cursor.getString(4));
            list.add(evaluate);
            cursor.moveToNext();
        }
        return list;
    }

    public Boolean insertDanhGia(int idchapter, int idtaikhoan, float sosao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("idchapter", idchapter);
        values.put("idtaikhoan",idtaikhoan);
        values.put("sosao",sosao);

        long result= db.insert("danhgia",null,values);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean updateDanhGia(int idchapter, int idtaikhoan, float sosao){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("sosao",sosao);

        long kq=db.update("danhgia",values,"idtaikhoan=? and idchapter=?",new String[]{""+idtaikhoan,""+idchapter});
        if(kq==-1) return false;
        else
            return true;
    }

    public Boolean checkDanhGia(int idtaikhoan,int idchapter){
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select id from danhgia where idchapter=? and idtaikhoan=?",new String[] {""+idchapter,""+idtaikhoan});
        cursor.moveToFirst();
        if(cursor.getCount()>0){
            return true;
        }else {
            return false;
        }
    }

    public float getSoSaoChapter(int idchapter){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select round(avg(sosao),2) from danhgia where idchapter=?",new String[] {""+idchapter});
        cursor.moveToFirst();

        float sosao=0;
        if(cursor.getCount()>0){
            sosao=cursor.getFloat(0);
        }
        return sosao;
    }

    public int getTongDanhGia(int idtaikhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(*) from danhgia where idtaikhoan=?",new String[] {""+idtaikhoan});
        cursor.moveToFirst();

        int tongdanhgia=0;
        if(cursor.getCount()>0){
            tongdanhgia=cursor.getInt(0);
        }
        return tongdanhgia;
    }

    public int getTongDanhGiaTruyen(int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(*) from danhgia where idchapter in (select id from chapter where idtruyen=?)",new String[] {""+idtruyen});
        cursor.moveToFirst();

        int tongdanhgia=0;
        if(cursor.getCount()>0){
            tongdanhgia=cursor.getInt(0);
        }
        return tongdanhgia;
    }
    //Thống kê
    public Statistical getThongKe(int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from thongke where idtruyen=?",new String[] {""+idtruyen});
        cursor.moveToFirst();

        Statistical statistical =new Statistical(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getFloat(3));
        return statistical;
    }

    public Statistical getThongKeById(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from thongke where id=?",new String[] {""+id});
        cursor.moveToFirst();

        Statistical statistical =new Statistical(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getFloat(3));
        return statistical;
    }

    public ArrayList<Statistical> getListThongKe(){
        ArrayList<Statistical> list=new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from thongke",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Statistical statistical = new Statistical(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getFloat(3));
            list.add(statistical);
            cursor.moveToNext();
        }
        return list;
    }

    public Boolean updateThongKeLuotXem(Statistical statistical){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("tongluotxem", statistical.getTotalView()+1);

        long kq=db.update("thongke",values,"idtruyen=?",new String[]{String.valueOf(statistical.getIdStory())});
        if(kq==-1) return false;
        else
            return true;
    }

    public Boolean updateThongKeSoSaoTB(int id,float value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("sosaotb", value);

        long kq=db.update("thongke",values,"idtruyen=?",new String[]{String.valueOf(id)});
        if(kq==-1) return false;
        else
            return true;
    }

    public ArrayList<String> getTongThongKe(){
        ArrayList<String> list=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor=db.rawQuery("select count(*) from truyen",null);
        cursor.moveToFirst();
        list.add(""+cursor.getInt(0));

        Cursor cursor1=db.rawQuery("select count(*) from chapter",null);
        cursor1.moveToFirst();
        list.add(""+cursor1.getInt(0));

        Cursor cursor2=db.rawQuery("select sum(tongluotxem) from thongke",null);
        cursor2.moveToFirst();
        list.add(""+cursor2.getInt(0));

        Cursor cursor3=db.rawQuery("select count(*) from danhgia",null);
        cursor3.moveToFirst();
        list.add(""+cursor3.getInt(0));

        Cursor cursor4=db.rawQuery("select count(*) from binhluan",null);
        cursor4.moveToFirst();
        list.add(""+cursor4.getInt(0));

        return list;
    }
    //Thông báo
    public ArrayList<Notification> getListThongBao(){
        ArrayList<Notification> list=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select * from thongbao order by ngaydang desc",null);
        cursor.moveToFirst();
        while (cursor.isAfterLast() == false){
            Notification notification =new Notification(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getString(3));
            list.add(notification);
            cursor.moveToNext();
        }
        return list;
    }

    public int countThongBaoNow(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select count(*) from thongbao where ngaydang=date('now')",null);
        cursor.moveToFirst();
        int count=0;
        if(cursor.getCount()>0){
            count=cursor.getInt(0);
        }
        return count;
    }

    //Trung bình đánh giá
    public float TBDanhGiaTruyen(int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select round(avg(sosao),2) from danhgia where idchapter in (select id from chapter where idtruyen=?)",new String[] {""+idtruyen});
        cursor.moveToFirst();
        float count=0;
        if(cursor.getCount()>0){
            count=cursor.getInt(0);
        }
        return count;
    }
    //Tổng lượt xem
    public int TongLuotXemTruyen(int idtruyen){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor=db.rawQuery("select sum(soluotxem) from chapter where idtruyen=? ",new String[] {""+idtruyen});
        cursor.moveToFirst();
        int count=0;
        if(cursor.getCount()>0){
            count=cursor.getInt(0);
        }
        return count;
    }
}
