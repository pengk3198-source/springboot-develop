package me.scpark;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class MenberRepositoryTest {
    @Autowired


    private MenberRepository menberRepository;
    @Sql("/insert-members.sql")
    @DisplayName("MemberRepository를 통해 member 테이블의 모든 레코드(3개) 가져오기.")


    @Test
    public void  getAllMenbers(){

        List<Menber> menbers=menberRepository.findAll();

       assertThat(menbers.size()).isEqualTo(3);


    }

}