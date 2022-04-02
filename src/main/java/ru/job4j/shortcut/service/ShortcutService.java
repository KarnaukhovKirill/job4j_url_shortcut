package ru.job4j.shortcut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.shortcut.model.Shortcut;
import ru.job4j.shortcut.repository.ShortcutRepository;

@Service
public class ShortcutService {
    @Autowired
    private ShortcutRepository shortcutRepository;

    @Transactional
    public Shortcut findByCode(String code) {
        Shortcut shortcut = shortcutRepository.findShortcutByCode(code);
        if (shortcut != null) {
            shortcutRepository.updateCounter(shortcut.getId());
        } else {
            throw new IllegalArgumentException("Url с таким кодом не существует. "
                    + "Попробуйте зарегистрировать url с помощью /convert.");
        }
        return shortcut;
    }
}
