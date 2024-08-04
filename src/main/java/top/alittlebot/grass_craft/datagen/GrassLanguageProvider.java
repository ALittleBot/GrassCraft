package top.alittlebot.grass_craft.datagen;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.item.GrassCreativeTab;
import top.alittlebot.grass_craft.item.GrassItems;

public class GrassLanguageProvider {
    public static void onGatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var packOutput = gen.getPackOutput();
        gen.addProvider(event.includeClient(), new EnglishLanguageProvider(packOutput));
        gen.addProvider(event.includeClient(), new ChineseLanguageProvider(packOutput));
    }

    public static class EnglishLanguageProvider extends LanguageProvider {
        public EnglishLanguageProvider(PackOutput gen) {
            super(gen, GrassCraft.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            this.add(GrassItems.GRASS_STICK_ITEM.get(), "Grass Stick");
            this.add(GrassItems.STAFF_OF_GRASS_ITEM.get(), "Staff of Grass");
            this.add(GrassItems.GRASS_BALL_ITEM.get(), "Grass Ball");
            this.add(GrassEffects.GROW_GRASS.get(), "Grow Grass");

            this.add(GrassCreativeTab.GRASS_TAB_ID, "Grass Tab");
        }
    }


    public static class ChineseLanguageProvider extends LanguageProvider {
        public ChineseLanguageProvider(PackOutput gen) {
            super(gen, GrassCraft.MOD_ID, "zh_cn");
        }

        @Override
        protected void addTranslations() {
            this.add(GrassItems.GRASS_STICK_ITEM.get(), "草棍");
            this.add(GrassItems.STAFF_OF_GRASS_ITEM.get(), "草之杖");
            this.add(GrassItems.GRASS_BALL_ITEM.get(), "草球");
            this.add(GrassEffects.GROW_GRASS.get(), "生草");

            this.add(GrassCreativeTab.GRASS_TAB_ID, "草栏");
        }
    }
}
