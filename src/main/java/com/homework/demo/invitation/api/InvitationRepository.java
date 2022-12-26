package com.homework.demo.invitation.api;

import com.homework.demo.invitation.api.InvitationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends JpaRepository<InvitationEntity, Long> {

}
