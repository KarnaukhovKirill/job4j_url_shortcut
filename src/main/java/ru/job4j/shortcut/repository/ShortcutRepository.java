package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Shortcut;

public interface ShortcutRepository extends CrudRepository<Shortcut, Long> {
    public Shortcut findShortcutByCode(String code);
}
