DELIMITER $$
DROP FUNCTION IF EXISTS `com_split`$$
CREATE FUNCTION `com_split`(vars VARCHAR(255)CHARSET utf8) RETURNS TEXT(65535) CHARSET utf8
  BEGIN
    DECLARE len INT DEFAULT 0;
    DECLARE pos INT DEFAULT 1;
    DECLARE Tmp TEXT(65535) DEFAULT '';
    DECLARE other VARCHAR(255) DEFAULT '';
    DECLARE num VARCHAR(255) DEFAULT '';
    SET len=CHAR_LENGTH(vars);
    loop_lable:LOOP
      IF pos > len THEN
        LEAVE loop_lable;
      end if;
      IF MID(vars,pos,1)REGEXP'[0-9]' THEN
        SET num=CONCAT(num,MID(vars,pos,1));
        IF pos < len && NOT MID(vars,pos+1,1)REGEXP'[0-9]' OR pos = len THEN
          IF CHAR_LENGTH(num) < 14 THEN
            SET Tmp=CONCAT(Tmp,LPAD(num+0, 14, 0));
          ELSE
            SET Tmp=CONCAT(Tmp,num+0);
          END IF;
          SET num=''; # 置空,备下次使用
        END IF;
      ELSE
        SET other=CONCAT(other,MID(vars,pos,1));
        IF pos < len && MID(vars,pos+1,1)REGEXP'[0-9]' OR pos = len THEN
          SET Tmp=CONCAT(Tmp,other);
          SET other=''; # 置空,备下次使用
        END IF;
      END IF;
      SET pos = pos+1;
    END LOOP;
    #RETURN REVERSE(Tmp);
    RETURN Tmp;
  END $$
DELIMITER ;

SELECT com_split('打05新01Ad就19');
