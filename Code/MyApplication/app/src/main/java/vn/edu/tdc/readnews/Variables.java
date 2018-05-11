package vn.edu.tdc.readnews;

public class Variables {
    public static final String[] PAPERS = {"Vnexpress", "Vietnamnet", "DanTri", "TuoiTre", "NguoiLaoDong","24h.com.vn"};

    public static final int[] ICONS = {
            R.drawable.vne_logo, R.drawable.logo_nvnet,
            R.drawable.logo_dantri, R.drawable.logo_tuoitre,
            R.drawable.logo_nld,R.drawable.logo_24h
    };

    //public static final int[] ICONS_BAIBAO = {R.drawable.icon_bai_bao,R.drawable.icon_bai_bao};
    public static final int[] ICONS_ITEM_VNE = {
            R.drawable.icon_trang_chu, R.drawable.icon_thoi_su, R.drawable.icon_the_gioi,
            R.drawable.icon_giai_tri, R.drawable.icon_the_thao, R.drawable.icon_phap_luat, R.drawable.icon_giao_duc,
            R.drawable.icon_suc_khoe, R.drawable.icon_gia_dinh, R.drawable.icon_du_lich, R.drawable.icon_cong_nghe,
            R.drawable.icon_so_hoa, R.drawable.icon_xe, R.drawable.icon_cong_dong, R.drawable.icon_tam_su,
            R.drawable.icon_cuoi};
    public static final int[] ICONS_ITEM_24H = {
            R.drawable.icon_thoi_su, R.drawable.icon_the_gioi, R.drawable.icon_the_thao, R.drawable.icon_giai_tri,
            R.drawable.icon_phap_luat, R.drawable.icon_thoi_trang, R.drawable.icon_phim, R.drawable.icon_giao_duc,
            R.drawable.icon_suc_khoe, R.drawable.icon_xe, R.drawable.icon_thi_truong, R.drawable.icon_cuoi
    };

    // vnexpress.net
    public static final String[] VNEXPRESS_CATEGORIES = {
            "Trang Chủ", "Thời Sự", "Thế Giới",
            "Giải Trí", "Thể Thao", "Pháp Luật", "Giáo Dục",
            "Sức Khỏe", "Gia Đình", "Du Lịch", "Khoa Học",
            "Số Hóa", "Xe", "Cộng Đồng", "Tâm Sự",
            "Cười"};
    public static final String[] VNEXPRESS_LINKS = {
            "http://vnexpress.net/rss/tin-moi-nhat.rss",
            "http://vnexpress.net/rss/thoi-su.rss",
            "http://vnexpress.net/rss/the-gioi.rss",
            "http://vnexpress.net/rss/giai-tri.rss",
            "http://vnexpress.net/rss/the-thao.rss",
            "http://vnexpress.net/rss/phap-luat.rss",
            "http://vnexpress.net/rss/giao-duc.rss",
            "http://vnexpress.net/rss/suc-khoe.rss",
            "http://vnexpress.net/rss/gia-dinh.rss",
            "http://vnexpress.net/rss/du-lich.rss",
            "http://vnexpress.net/rss/khoa-hoc.rss",
            "http://vnexpress.net/rss/so-hoa.rss",
            "http://vnexpress.net/rss/oto-xe-may.rss",
            "http://vnexpress.net/rss/cong-dong.rss",
            "http://vnexpress.net/rss/tam-su.rss",
            "http://vnexpress.net/rss/cuoi.rss"};

    public static final String[] H24_CATEGORISES = {
            "Tin Tức Trong Ngày", "Thế Giới", "Bóng Đá", "Giải Trí",
            "An Ninh - Hình Sự", "Thời Trang", "Phim", "Giáo Dục - Du Học",
            "Sức Khỏe Đời Sống", "Xe", "Thị Trường - Tiêu Dùng", "Cười 24h"};

    public static final String[] H24_LINKS = {
            "http://www.24h.com.vn/upload/rss/tintuctrongngay.rss",
            "http://www.24h.com.vn/upload/rss/tintucquocte.rss",
            "http://www.24h.com.vn/upload/rss/bongda.rss",
            "http://www.24h.com.vn/upload/rss/giaitri.rss",
            "http://www.24h.com.vn/upload/rss/anninhhinhsu.rss",
            "http://www.24h.com.vn/upload/rss/thoitrang.rss",
            "http://www.24h.com.vn/upload/rss/phim.rss",
            "http://www.24h.com.vn/upload/rss/giaoducduhoc.rss",
            "http://www.24h.com.vn/upload/rss/suckhoedoisong.rss",
            "http://www.24h.com.vn/upload/rss/otoxemay.rss",
            "http://www.24h.com.vn/upload/rss/thitruongtieudung.rss",
            "http://www.24h.com.vn/upload/rss/cuoi24h.rss"
    };

    public static final String[] VNNET_CATEGORIES = {
            "Trang Chủ", "Xã Hội", "Công Nghệ", "Kinh Doanh",
            "Giáo Dục", "Chính Trị", "Giải Trí", "Sức Khỏe",
            "Thể Thao", "Đời Sống", "Quốc Tế", "Bất Động Sản",
            "Bạn Đọc", "Tin Mới Nóng", "Tin Nổi Bật", "Tuần Việt Nam",
            "Góc Nhìn Thẳng"
    };

    public static final String[] VNNET_LINKS = {
            "http://vietnamnet.vn/rss/home.rss",
            "http://vietnamnet.vn/rss/xa-hoi.rss",
            "http://vietnamnet.vn/rss/cong-nghe.rss",
            "http://vietnamnet.vn/rss/kinh-doanh.rss",
            "http://vietnamnet.vn/rss/giao-duc.rss",
            "http://vietnamnet.vn/rss/chinh-tri.rss",
            "http://vietnamnet.vn/rss/giai-tri.rss",
            "http://vietnamnet.vn/rss/suc-khoe.rss",
            "http://vietnamnet.vn/rss/the-thao.rss",
            "http://vietnamnet.vn/rss/doi-song.rss",
            "http://vietnamnet.vn/rss/quoc-te.rss",
            "http://vietnamnet.vn/rss/bat-dong-san.rss",
            "http://vietnamnet.vn/rss/ban-doc-phap-luat.rss",
            "http://vietnamnet.vn/rss/moi-nong.rss",
            "http://vietnamnet.vn/rss/tin-noi-bat.rss",
            "http://vietnamnet.vn/rss/tuanvietnam.rss",
            "http://vietnamnet.vn/rss/goc-nhin-thang.rss"
    };

    public static final int[] ICONS_ITEM_VNNET = {
            R.drawable.icon_trang_chu, R.drawable.icon_phap_luat, R.drawable.icon_cong_nghe,
            R.drawable.icon_thi_truong, R.drawable.icon_giao_duc, R.drawable.icon_chinh_tri,
            R.drawable.icon_giai_tri, R.drawable.icon_suc_khoe, R.drawable.icon_the_thao,
            R.drawable.icon_gia_dinh, R.drawable.icon_the_gioi, R.drawable.icon_bat_dong_san,
            R.drawable.icon_ban_doc, R.drawable.icon_tin_moi_nong, R.drawable.icon_tin_noi_bat,
            R.drawable.icon_viet_nam, R.drawable.icon_goc_nhin

    };

    public static final String[] DANTRI_CATEGORIES = {
            "Trang Chủ", "Sức Khỏe", "Xã Hội", "Giải Trí",
            "Giáo Dục", "Thể Thao", "Thế Giới", "Kinh Doanh",
            "Ô Tô - Xe Máy", "Sức Mạnh Số", "Tình Yêu - Giới Tính", "Chuyện Lạ",
            "Việc Làm"
    };
    public static final String[] DANTRI_LINKS = {
            "http://dantri.com.vn/trangchu.rss",
            "http://dantri.com.vn/suc-khoe.rss",
            "http://dantri.com.vn/xa-hoi.rss",
            "http://dantri.com.vn/giai-tri.rss",
            "http://dantri.com.vn/giao-duc-khuyen-hoc.rss",
            "http://dantri.com.vn/the-thao.rss",
            "http://dantri.com.vn/the-gioi.rss",
            "http://dantri.com.vn/kinh-doanh.rss",
            "http://dantri.com.vn/o-to-xe-may.rss",
            "http://dantri.com.vn/suc-manh-so.rss",
            "http://dantri.com.vn/tinh-yeu-gioi-tinh.rss",
            "http://dantri.com.vn/chuyen-la.rss",
            "http://dantri.com.vn/viec-lam.rss"
    };
    public static final int[] ICONS_ITEM_DANTRI = {
            R.drawable.icon_trang_chu, R.drawable.icon_suc_khoe, R.drawable.icon_phap_luat,
            R.drawable.icon_giai_tri, R.drawable.icon_giao_duc, R.drawable.icon_the_thao,
            R.drawable.icon_the_gioi, R.drawable.icon_thi_truong, R.drawable.icon_xe,
            R.drawable.icon_so_hoa, R.drawable.icon_tinh_yeu, R.drawable.icon_cuoi,
            R.drawable.icon_ban_doc
    };

    // tuoi tre
    public static final String[] TUOITRE_CATEGORIES = {
            "Trang Chủ", "Thế Giới", "Kinh Tế", "Giáo Dục",
            "Giải Trí", "Nhịp Sống Số", "Du Lịch", "Thời Sự",
            "Pháp Luật", "Sống Khỏe", "Thế Thao", "Nhịp Sống Trẻ",
            "Bạn Đọc"
    };
    public static final String[] TUOITRE_LINKS = {
            "http://tuoitre.vn/rss/tin-moi-nhat.rss",
            "http://tuoitre.vn/rss/the-gioi.rss",
            "http://tuoitre.vn/rss/kinh-doanh.rss",
            "http://tuoitre.vn/rss/giao-duc.rss",
            "http://tuoitre.vn/rss/thu-gian.rss",
            "http://tuoitre.vn/rss/nhip-song-so.rss",
            "http://tuoitre.vn/rss/du-lich.rss",
            "http://tuoitre.vn/rss/thoi-su.rss",
            "http://tuoitre.vn/rss/phap-luat.rss",
            "http://tuoitre.vn/rss/suc-khoe.rss",
            "http://tuoitre.vn/rss/the-thao.rss",
            "http://tuoitre.vn/rss/nhip-song-tre.rss",
            "http://tuoitre.vn/rss/ban-doc-lam-bao.rss"
    };

    public static final int[] ICONS_ITEM_TUOITRE = {
            R.drawable.icon_trang_chu, R.drawable.icon_the_gioi, R.drawable.icon_thi_truong, R.drawable.icon_giao_duc,
            R.drawable.icon_giai_tri, R.drawable.icon_so_hoa, R.drawable.icon_du_lich, R.drawable.icon_chinh_tri,
            R.drawable.icon_phap_luat, R.drawable.icon_suc_khoe, R.drawable.icon_the_thao, R.drawable.icon_gia_dinh,
            R.drawable.icon_ban_doc
    };

    // NLD
    public static final String[] NLD_CATEGORIES = {
            "Tin Mới Nhất", "Thời Sự Trong Nước", "Thời Sự Quốc Tế", "Kinh Tế",
            "Giáo Dục", "Pháp Luật", "Văn Nghệ", "Thể Thao",
            "Sức Khỏe", "Tình Yêu - Hôn Nhân", "Bạn Đọc", "Công Nghệ",
            "Thời Trang - Làm Đẹp", "Thư Giãn"
    };

    public static final String[] NLD_LINKS = {
            "http://nld.com.vn/tin-moi-nhat.rss",
            "http://nld.com.vn/thoi-su-trong-nuoc.rss",
            "http://nld.com.vn/thoi-su-quoc-te.rss",
            "http://nld.com.vn/kinh-te.rss",
            "http://nld.com.vn/giao-duc-khoa-hoc.rss",
            "http://nld.com.vn/phap-luat.rss",
            "http://nld.com.vn/van-hoa-van-nghe.rss",
            "http://nld.com.vn/the-thao.rss",
            "http://nld.com.vn/suc-khoe.rss",
            "http://nld.com.vn/tinh-yeu-hon-nhan.rss",
            "http://nld.com.vn/ban-doc.rss",
            "http://nld.com.vn/cong-nghe-thong-tin.rss",
            "http://nld.com.vn/thoi-trang-lam-dep.rss",
            "http://nld.com.vn/thu-gian.rss"
    };

    public static final int[] ICONS_ITEM_NLD = {
            R.drawable.icon_tin_noi_bat, R.drawable.icon_thoi_su, R.drawable.icon_the_gioi, R.drawable.icon_thi_truong,
            R.drawable.icon_giao_duc, R.drawable.icon_phap_luat, R.drawable.icon_giai_tri, R.drawable.icon_the_thao,
            R.drawable.icon_suc_khoe, R.drawable.icon_tinh_yeu, R.drawable.icon_ban_doc, R.drawable.icon_so_hoa,
            R.drawable.icon_thoi_trang, R.drawable.icon_cuoi
    };

    //thanh nien

    public static final String[] TN_CATEGORIES = {
            "Trang Chủ", "Thời Sự", "Pháp Luật", "Thể Thao", "Việc Làm",
            "Sức Khỏe", "Tuyển Dụng", "Thế Giới", "Chính Trị",
            "Văn Hóa - Nghệ Thuật", "Đời Sống", "Kinh Tế", "Thế Giới Trẻ",
            "Giáo Dục", "Công Nghệ Thông Tin", "Xe", "Game"
    };

    public static final String[] TN_LINKS = {
            "https://thanhnien.vn/rss/home.rss",
            "https://thanhnien.vn/rss/viet-nam.rss",
            "https://thanhnien.vn/rss/viet-nam/phap-luat.rss",
            "https://thethao.thanhnien.vn/rss/home.rss",
            "https://thanhnien.vn/rss/thoi-su/viec-lam.rss",
            "https://thanhnien.vn/rss/doi-song/suc-khoe.rss",
            "https://thanhnien.vn/rss/viec-lam/tuyen-dung.rss",
            "https://thanhnien.vn/rss/the-gioi.rss",
            "https://thanhnien.vn/rss/chinh-tri.rss",
            "https://thanhnien.vn/rss/van-hoa-nghe-thuat.rss",
            "https://thanhnien.vn/rss/doi-song.rss",
            "https://thanhnien.vn/rss/kinh-te.rss",
            "https://thanhnien.vn/rss/the-gioi-tre.rss",
            "https://thanhnien.vn/rss/giao-duc.rss",
            "https://thanhnien.vn/rss/cong-nghe-thong-tin.rss",
            "https://xe.thanhnien.vn/rss/home.rss",
            "https://game.thanhnien.vn/rss/home.rss"
    };

    public static final int[] ICONS_ITEM_TN = {
            R.drawable.icon_trang_chu, R.drawable.icon_thoi_su, R.drawable.icon_phap_luat, R.drawable.icon_the_thao,
            R.drawable.icon_viet_nam, R.drawable.icon_suc_khoe, R.drawable.icon_bat_dong_san, R.drawable.icon_the_gioi,
            R.drawable.icon_chinh_tri, R.drawable.icon_giai_tri, R.drawable.icon_gia_dinh, R.drawable.icon_thi_truong,
            R.drawable.icon_so_hoa, R.drawable.icon_giao_duc, R.drawable.icon_cong_nghe, R.drawable.icon_xe,
             R.drawable.icon_goc_nhin};



    //tien phong

    public static final String[] TP_CATEGORIES = {
            "Xã Hội", "Kinh Tế", "Địa Ốc", "Thế Giới", "Giới Trẻ",
            "Xe", "Pháp Luật", "Thể Thao", "Giải Trí",
            "Giáo Dục", "Công Nghệ", "Tuyển Sinh", "Học Sinh - Sinh Viên",
            "Gia Đình", "Hoa Hậu", "Tin 99s", "Bạn Đọc"
    };

    public static final String[] TP_LINKS = {
            "https://www.tienphong.vn/rss/xa-hoi-2.rss",
            "https://www.tienphong.vn/rss/kinh-te-3.rss",
            "https://www.tienphong.vn/rss/dia-oc-166.rss",
            "https://www.tienphong.vn/rss/the-gioi-5.rss",
            "https://www.tienphong.vn/rss/gioi-tre-4.rss",
            "https://www.tienphong.vn/rss/xe-113.rss",
            "https://www.tienphong.vn/rss/phap-luat-12.rss",
            "https://www.tienphong.vn/rss/the-thao-11.rss",
            "https://www.tienphong.vn/rss/giai-tri-36.rss",
            "https://www.tienphong.vn/rss/giao-duc-71.rss",
            "https://www.tienphong.vn/rss/cong-nghe-45.rss",
            "https://www.tienphong.vn/rss/tuyen-sinh-155.rss",
            "https://www.tienphong.vn/rss/hoc-sinh-sinh-vien-186.rss",
            "https://www.tienphong.vn/rss/gia-dinh-suc-khoe-210.rss",
            "https://www.tienphong.vn/rss/hoa-hau-253.rss",
            "https://www.tienphong.vn/rss/tin-99s-236.rss",
            "https://www.tienphong.vn/rss/ban-doc-15.rss"
    };

    public static final int[] ICONS_ITEM_TP = {
            R.drawable.icon_trang_chu, R.drawable.icon_thi_truong, R.drawable.icon_bat_dong_san, R.drawable.icon_the_gioi,
            R.drawable.icon_so_hoa, R.drawable.icon_xe, R.drawable.icon_phap_luat, R.drawable.icon_the_thao,
            R.drawable.icon_giai_tri, R.drawable.icon_giao_duc, R.drawable.icon_cong_nghe, R.drawable.icon_about, R.drawable.icon_chinh_tri,
            R.drawable.icon_gia_dinh, R.drawable.icon_cong_dong, R.drawable.icon_tin_noi_bat, R.drawable.icon_ban_doc,
            };




    // All
    public static final String[][] CATEGORIES = {
            VNEXPRESS_CATEGORIES, DANTRI_CATEGORIES,
            TUOITRE_CATEGORIES, NLD_CATEGORIES,H24_CATEGORISES,TN_CATEGORIES, VNNET_CATEGORIES, TP_CATEGORIES
    };
    public static final String[][] LINKS = {
            VNEXPRESS_LINKS, DANTRI_LINKS,
            TUOITRE_LINKS, NLD_LINKS ,H24_LINKS, TN_LINKS, VNNET_LINKS, TP_LINKS
    };
    public static final int[][] ICON_ITEM = {
            ICONS_ITEM_VNE, ICONS_ITEM_DANTRI,
            ICONS_ITEM_TUOITRE, ICONS_ITEM_NLD, ICONS_ITEM_24H, ICONS_ITEM_TN, ICONS_ITEM_VNNET, ICONS_ITEM_TP
    };
    //
    public static final String PAPER = "paper";
    public static final String CATEGORY = "category";
    public static final String LINK = "link";
    public static final String TITLE = "title";
}