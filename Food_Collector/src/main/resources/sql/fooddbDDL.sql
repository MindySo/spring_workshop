use food_db;
-- main_food_detail과 nutrition을 합쳐야 할 것 같다. -> 합침
-- 요리 테이블에도 nutrition을 두는게 나을 것 같다.
-- 그러면 nomnom_db에서 food 테이블에는 food_name이 없어도 된다.(또 없어도 되는 컬럼 있는지 확인)
-- food테이블 명칭을 바꿔야 한다 ingredient로? 
-- 요리 테이블
CREATE TABLE main_food (
    main_food_code VARCHAR(20) PRIMARY KEY,   -- D015039
    main_food_name VARCHAR(255) NOT NULL,      -- "짬뽕덮밥"
    upper_group VARCHAR(100),
	sub_group VARCHAR(100),
	main_food_weight DOUBLE
);

-- 재료 테이블
CREATE TABLE food (
    food_code VARCHAR(20) PRIMARY KEY,       -- F00017, F02825 등
    food_name VARCHAR(255) NOT NULL,          -- "멥쌀, 백미, 생것" 등
	fd_name VARCHAR(100),
    group_name VARCHAR(100),
	allergy_info TEXT,
	image_url TEXT
);

-- 요리-재료 매핑
CREATE TABLE main_food_detail (
    id INT PRIMARY KEY AUTO_INCREMENT,
    main_food_code VARCHAR(20),
    food_code VARCHAR(20),       	 -- FK to food
    food_weight DOUBLE,				-- food_Weight
    energy_qy DOUBLE,
    water_qy DOUBLE,
    prot_qy DOUBLE,
    ntrfs_qy DOUBLE,
    ashs_qy DOUBLE,
    carbohydrate_qy DOUBLE,
    sugar_qy DOUBLE,
    fibtg_qy DOUBLE,
    aat19_qy DOUBLE,
    aae10a_qy DOUBLE,
    aane_qy DOUBLE,
    fafref_qy DOUBLE,
    faessf_qy DOUBLE,
    fasatf_qy DOUBLE,
    famsf_qy DOUBLE,
    fapuf_qy DOUBLE,
    clci_qy DOUBLE,
    irn_qy DOUBLE,
    mg_qy DOUBLE,
    phph_qy DOUBLE,
    ptss_qy DOUBLE,
    na_qy DOUBLE,
    zn_qy DOUBLE,
    cu_qy DOUBLE,
    mn_qy DOUBLE,
    se_qy DOUBLE,
    mo_qy DOUBLE,
    id_qy DOUBLE,
    rtnl_qy DOUBLE,
    catn_qy DOUBLE,
    vitd_qy DOUBLE,
    vite_qy DOUBLE,
    vitk1_qy DOUBLE,
    vtmn_b1_qy DOUBLE,
    vtmn_b2_qy DOUBLE,
    nacn_qy DOUBLE,
    pantac_qy DOUBLE,
    pyrxn_qy DOUBLE,
    biot_qy DOUBLE,
    fol_qy DOUBLE,
    vitb12_qy DOUBLE,
    vtmn_c_qy DOUBLE,
    chole_qy DOUBLE,
    nacl_qy DOUBLE,
    ref_qy DOUBLE,
    FOREIGN KEY (main_food_code) REFERENCES main_food(main_food_code),
    FOREIGN KEY (food_code) REFERENCES food(food_code)
);

ALTER TABLE main_food_detail
ADD CONSTRAINT uq_mainfood_food UNIQUE (main_food_code, food_code);



use food_db;
show table status like 'main_food_detail';
select * from main_food limit 0, 10000;
select * from food limit 0, 10000;
select * from main_food_detail limit 0, 10000;


-- 요리의 영양성분
-- CREATE TABLE nutrition (
--     nutrition_id INT PRIMARY KEY AUTO_INCREMENT,
--     food_code VARCHAR(20) NOT NULL,         -- FK to food
--     weight DOUBLE,                          -- food_Weight
--     energy_qy DOUBLE,
--     water_qy DOUBLE,
--     prot_qy DOUBLE,
--     ntrfs_qy DOUBLE,
--     ashs_qy DOUBLE,
--     carbohydrate_qy DOUBLE,
--     sugar_qy DOUBLE,
--     fibtg_qy DOUBLE,
--     aat19_qy DOUBLE,
--     aae10a_qy DOUBLE,
--     aane_qy DOUBLE,
--     fafref_qy DOUBLE,
--     faessf_qy DOUBLE,
--     fasatf_qy DOUBLE,
--     famsf_qy DOUBLE,
--     fapuf_qy DOUBLE,
--     clci_qy DOUBLE,
--     irn_qy DOUBLE,
--     mg_qy DOUBLE,
--     phph_qy DOUBLE,
--     ptss_qy DOUBLE,
--     na_qy DOUBLE,
--     zn_qy DOUBLE,
--     cu_qy DOUBLE,
--     mn_qy DOUBLE,
--     se_qy DOUBLE,
--     mo_qy DOUBLE,
--     id_qy DOUBLE,
--     rtnl_qy DOUBLE,
--     catn_qy DOUBLE,
--     vitd_qy DOUBLE,
--     vite_qy DOUBLE,
--     vitk1_qy DOUBLE,
--     vtmn_b1_qy DOUBLE,
--     vtmn_b2_qy DOUBLE,
--     nacn_qy DOUBLE,
--     pantac_qy DOUBLE,
--     pyrxn_qy DOUBLE,
--     biot_qy DOUBLE,
--     fol_qy DOUBLE,
--     vitb12_qy DOUBLE,
--     vtmn_c_qy DOUBLE,
--     chole_qy DOUBLE,
--     nacl_qy DOUBLE,
--     ref_qy DOUBLE,
--     FOREIGN KEY (food_code) REFERENCES food(food_code)
-- );



