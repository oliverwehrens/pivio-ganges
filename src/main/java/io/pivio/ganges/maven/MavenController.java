package io.pivio.ganges.maven;

import io.pivio.ganges.VersionNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MavenController {

    MavenRepositoryService mavenRepositoryService;

    public MavenController(MavenRepositoryService mavenRepositoryService) {
        this.mavenRepositoryService = mavenRepositoryService;
    }

    @GetMapping("/maven/{groupId}/{name}/{version:.+}")
    public Result query(@PathVariable String groupId, @PathVariable String name, @PathVariable String version) {
        return mavenRepositoryService.getVersion(groupId, name, version).orElseThrow(() -> new VersionNotFoundException(groupId + ":" + name + ":" + version));
    }


}
