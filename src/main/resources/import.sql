INSERT INTO `poslovnainformatikadb`.`mesto` (`grad`, `postanski_broj`,`obrisano`) VALUES ('Beograd', '11000',false);
INSERT INTO `poslovnainformatikadb`.`mesto` (`grad`, `postanski_broj`,`obrisano`) VALUES ('Novi Sad', '21000',false);
INSERT INTO `poslovnainformatikadb`.`mesto` (`grad`, `postanski_broj`,`obrisano`) VALUES ('Sremska MItrovica', '22000',false);
INSERT INTO `poslovnainformatikadb`.`mesto` (`grad`, `postanski_broj`,`obrisano`) VALUES ('Zrenjanin', '23000',false);
INSERT INTO `poslovnainformatikadb`.`mesto` (`grad`, `postanski_broj`,`obrisano`) VALUES ('Pancevo', '26000',false);
INSERT INTO `poslovnainformatikadb`.`mesto` (`grad`, `postanski_broj`,`obrisano`) VALUES ('Nis', '14000',false);

INSERT INTO `poslovnainformatikadb`.`preduzece` (`pib`, `adresa`, `email`, `naziv_preduzeca`, `tekuci_racun`, `telefon`, `mesto_id`) VALUES ('123456789', 'Bulevar Kralja Aleksandra', 'mojepreduzece@gmail.com', 'Moje preduzece', '12341234', '065221144', '1');

INSERT INTO `poslovnainformatikadb`.`poslovni_partner` (`pib`, `adresa`, `naziv_poslovnog_partnera`, `obrisano`, `tekuci_racun`, `tip_poslovnog_partnera`, `mesto_id`, `preduzece_id`) VALUES ('111222333', 'Bulevar Oslobodjenja', 'Tehnomanija', false, '433433', '0', '1', '1');
INSERT INTO `poslovnainformatikadb`.`poslovni_partner` (`pib`, `adresa`, `naziv_poslovnog_partnera`, `obrisano`, `tekuci_racun`, `tip_poslovnog_partnera`, `mesto_id`, `preduzece_id`) VALUES ('222244445', 'Zahumska', 'Media Market', false , '5353353', '0', '2', '1');
INSERT INTO `poslovnainformatikadb`.`poslovni_partner` (`pib`, `adresa`, `naziv_poslovnog_partnera`, `obrisano`, `tekuci_racun`, `tip_poslovnog_partnera`, `mesto_id`, `preduzece_id`) VALUES ('444343434', 'Nehruova', 'Music shop', false, '6363343', '1', '3', '1');

INSERT INTO `poslovnainformatikadb`.pdv (vrsta_pdv,obrisano) VALUES ('Opsti',false);
INSERT INTO `poslovnainformatikadb`.pdv (vrsta_pdv,obrisano) VALUES ('Poseban',false);

INSERT INTO `poslovnainformatikadb`.stopa_pdv (rok_vazenja,obrisano,procenat,pdv_id) VALUES ('2022-01-31 07:07:07.000',0,21,1);
INSERT INTO `poslovnainformatikadb`.stopa_pdv (rok_vazenja,obrisano,procenat,pdv_id) VALUES ('2022-12-12 07:07:07.000',0,8,2);
INSERT INTO `poslovnainformatikadb`.stopa_pdv (rok_vazenja,obrisano,procenat,pdv_id) VALUES ('2022-12-12 07:07:07.000',0,12,2);
INSERT INTO `poslovnainformatikadb`.stopa_pdv (rok_vazenja,obrisano,procenat,pdv_id) VALUES ('2021-02-14 07:07:07.000',0,10,2);

INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('bela tehnika',0,1,1);
INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('kucni aparati',0,1,2);
INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('klima uredjaji',0,1,1);
INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('TV',0,1,1);
INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('Muzicki uredjaji',0,1,1);
INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('Racunari',0,1,1);
INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('Kamere',0,1,1);
INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('Fotoaparati',0,1,2);
INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('Telefoni',0,1,2);
INSERT INTO `poslovnainformatikadb`.grupa_robe (naziv,obrisano,preduzece_id, pdv_id) VALUES ('Instrumenti',0,1,1);

INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'iPhone 12', false, 9);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'Samsung galaxy', false, 9);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'Bosch masina za sudove', false, 2);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'Lenovo i7 16GB', false, 6);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'HP i5 8GB', false, 6);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'Asus ROG i7 16GB', false, 6);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'Cannon', false, 8);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'Fender stratocaster', false, 10);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'Yamaha Compas NTX', false, 10);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'LG 32', false, 4);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'LG 51', false, 4);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'Toshiba 42', false, 4);
INSERT INTO `poslovnainformatikadb`.roba (jedinica_mere, naziv_robe, obrisano, grupa_robe_id) VALUES ('komad', 'Beko frizder neo frost', false, 1);

INSERT INTO `poslovnainformatikadb`.`cenovnik` (`kraj_roka_trajanja`, `obrisano`, `pocetak_roka_trajanja`, `poslovni_partner_id`, `preduzece_id`) VALUES ('2022-05-05', false, '2020-11-11', '1', '1');
INSERT INTO `poslovnainformatikadb`.`cenovnik` (`kraj_roka_trajanja`, `obrisano`, `pocetak_roka_trajanja`, `poslovni_partner_id`, `preduzece_id`) VALUES ('2024-07-05', false, '2019-11-11', '2', '1');
INSERT INTO `poslovnainformatikadb`.`cenovnik` (`kraj_roka_trajanja`, `obrisano`, `pocetak_roka_trajanja`, `poslovni_partner_id`, `preduzece_id`) VALUES ('2022-05-05', false, '2017-11-11', '3', '1');

INSERT INTO `poslovnainformatikadb`.`poslovna_godina` (`godina_poslovanja`, `obrisano`, `zakljucena_godina`) VALUES ('2021', false, false);

INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('12000', 1, false, 1);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('25000', 1, false, 2);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('17000', 1, false, 3);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('31000', 1, false, 4);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('45000', 1, false, 5);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('22000', 1, false, 6);

INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('12000', 2, false, 1);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('25000', 2, false, 2);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('17000', 2, false, 3);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('31000', 2, false, 4);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('45000', 2, false, 5);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('22000', 2, false, 6);

INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('12000', 3, false, 1);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('25000', 3, false, 2);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('17000', 3, false, 3);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('31000', 3, false, 4);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('45000', 3, false, 5);
INSERT INTO `poslovnainformatikadb`.`stavke_cenovnika` (`cena`, `cenovnik_id`, `obrisano`, `roba_id`) VALUES ('22000', 3, false, 6);