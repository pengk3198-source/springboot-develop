package me.scpark;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.Optional;

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
    @DisplayName("MemberRepository를 통해 이름이 'C'인 레코드 가져오기")
    @Sql("/insert-members.sql")
    @Test
    public void getMemberByName() {
        // 준비 given

        // 실행 when : select * from member where name =?
        Optional<Menber> optMember = menberRepository.findByName("C");
        Menber menber = optMember.get();

        // 검증 then
        assertThat(menber.getid()).isEqualTo(3);
    }



    @DisplayName("MemberRepository를 통해 id이 2인 레코드 가져오기")
    @Sql("/insert-members.sql")
    @Test
    public void getMemberByid(){
        //given



        // when
        Optional<Menber> optMember = menberRepository.findById(2L);
        Menber member = optMember.get();

        // then
        assertThat(member.getName()).isEqualTo("B");
    }

    @DisplayName("MemberRepository를 통해 이름이 '박성철'인 레코드 추가하기")
    @Test
    public void saveMenber() {
        // 준비 given
        Menber menber = new Menber("박성철");

        // 실행 when
        menberRepository.save(menber);

        // 검증 then
        List<Menber> result = menberRepository.findAll();

        assertThat(result.size()).isEqualTo(1);
        assertThat(result.get(0).getName()).isEqualTo("박성철");
    }
    @Sql("/insert-members.sql")
    @DisplayName("talbe menber id 3 delete")
    @Test
    public void deleteById3(){
       //given



        //when
        menberRepository.deleteById(3L);


        //then
        List<Menber> menber=menberRepository.findAll();
        assertThat(menber.size()).isEqualTo(2);

    }
    @Sql("/insert-members.sql")
    @DisplayName("테이블 member의 모든 레코드 삭제하기")
    @Test
    public void deleteAll() {
        // 준비 given
        List<Menber> menbers = menberRepository.findAll();
        assertThat(menbers.size()).isEqualTo(3);


        // 실행 when
        menberRepository.deleteAll();

        // 검증 then
        // member 테이블에서 모든 레코드를 가져왔더니 하나도 없더라.
        List<Menber> result = menberRepository.findAll();
        assertThat(result.size()).isZero();
    }

    @DisplayName("테이블 member name이 'A', 'B'인 레코드를 한 번에 삽입하기")
    @Test
    public void saveMenbers() {
        // 준비 given

        List<Menber> menbers = List.of(new Menber("A"),new Menber("B"));

        // 실행 when
        menberRepository.saveAll(menbers);

        // 검증 then
        List<Menber> result = menberRepository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
    @Sql("/insert-members.sql")
    @DisplayName("변경감지 기능을 통해 Id가 2인 레코드의 이름을 'B', 'BB'로 변경하기")
    @Test
    public void update(){
        Menber member = menberRepository.findById(2L).orElseThrow();

        // 실행 when
        member.setName("BB");

        menberRepository.flush();

        // 검증 then
        Menber result = menberRepository.findById(2L).orElseThrow();
        assertThat(result.getName()).isEqualTo("BB");


    }




}
    

