package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 repositoryV0 = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        final var member = new Member("memberV0", 10000);
        repositoryV0.save(member);



        final var findMember = repositoryV0.findById(member.getMemberId());
        log.info("findMember={}", findMember);

        Assertions.assertThat(findMember).isEqualTo(member);



        repositoryV0.update(member.getMemberId(), 20000);
        final var updatedMember = repositoryV0.findById(member.getMemberId());

        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(20000);



        repositoryV0.delete(member.getMemberId());

        Assertions.assertThatThrownBy(() -> repositoryV0.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}