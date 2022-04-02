package ru.job4j.shortcut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.shortcut.model.Shortcut;

public interface ShortcutRepository extends CrudRepository<Shortcut, Long> {
    public Shortcut findShortcutByCode(String code);
    @Modifying
    @Query("update Shortcut s set s.counter = s.counter + 1 where s.id = :id")
    public void updateCounter(@Param("id") long id);
}
