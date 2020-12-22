import javax.inject.Inject;
import org.gradle.api.Project;
import org.gradle.api.tasks.TaskCollection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile;

public class ProjectConfig {

    @NotNull
    private final Project project;

    @Inject
    public ProjectConfig(@NotNull Project project) {
        this.project = project;
    }

    @NotNull
    public TaskCollection<KotlinCompile> getKotlin() {
        return project.getTasks().withType(KotlinCompile.class);
    }
}
