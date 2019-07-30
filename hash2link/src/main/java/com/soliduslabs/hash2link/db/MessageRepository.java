package com.soliduslabs.hash2link.db;

import com.soliduslabs.hash2link.domain.Message;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface MessageRepository extends CrudRepository<Message, String> {

    @Cacheable(cacheNames = "MessageByHash")
    @Override
    Optional<Message> findById(String hash);

    Optional<Message> findByContent(String content);
}
