package top.alittlebot.grass_craft.datagen.provider;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.LanguageProvider;
import top.alittlebot.grass_craft.GrassCraft;
import top.alittlebot.grass_craft.effect.GrassEffects;
import top.alittlebot.grass_craft.GrassCreativeTab;
import top.alittlebot.grass_craft.entity.GrassEntity;
import top.alittlebot.grass_craft.item.GrassItems;
import top.alittlebot.grass_craft.item.potion.GrassPotions;

public class GrassLanguageProvider {

    public static class EnglishLanguageProvider extends LanguageProvider {
        public EnglishLanguageProvider(PackOutput gen) {
            super(gen, GrassCraft.MOD_ID, "en_us");
        }

        @Override
        protected void addTranslations() {
            this.add(GrassItems.GRASS_STICK_ITEM.get(), "Grass Stick");
            this.add(GrassItems.STAFF_OF_GRASS_ITEM.get(), "Staff of Grass");
            this.add(GrassItems.GRASS_BALL_ITEM.get(), "Grass Ball");
            this.add(GrassItems.VANILLA_ITEM.get(), "Vanilla");
            this.add(GrassItems.VANILLA_INGOT_ITEM.get(), "Vanilla Ingot");
            this.add(GrassItems.VANILLA_GLOVE_ITEM.get(), "Vanilla Glove");

            this.add(GrassItems.GRASS_TNT_ITEM.get(), "Grass TNT");

            this.add(GrassEffects.GROW_GRASS.get(), "Grow Grass");

            this.add(GrassPotions.POTION_KEY + GrassPotions.GRASS_POTION_ID, "Potion of Grass");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.GRASS_POTION_ID, "Splash Potion of Grass");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.GRASS_POTION_ID, "Lingering Potion of Grass");

            this.add(GrassEntity.GRASS_TNT_ENTITY.get(), "Primed Grass TNT");

            this.add("grass_craft.tooltip.vanilla_glove", "Idea from Mafuyu33, only works on grass");

            this.add(GrassCreativeTab.GRASS_TAB_ID, "Grass Craft");
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
            this.add(GrassItems.VANILLA_ITEM.get(), "香草");
            this.add(GrassItems.VANILLA_INGOT_ITEM.get(), "香草锭");
            this.add(GrassItems.VANILLA_GLOVE_ITEM.get(), "香草手套");

            this.add(GrassItems.GRASS_TNT_ITEM.get(), "香草TNT");

            this.add(GrassEffects.GROW_GRASS.get(), "生草");

            this.add(GrassPotions.POTION_KEY + GrassPotions.GRASS_POTION_ID, "生草药水");
            this.add(GrassPotions.SPLASH_POTION_KEY + GrassPotions.GRASS_POTION_ID, "喷溅型生草药水");
            this.add(GrassPotions.LINGERING_POTION_KEY + GrassPotions.GRASS_POTION_ID, "滞留型生草药水");

            this.add(GrassEntity.GRASS_TNT_ENTITY.get(), "点燃的香草TNT");

            this.add("grass_craft.tooltip.vanilla_glove", "想法来自马夫鱼33, 只能对草起作用");

            this.add(GrassCreativeTab.GRASS_TAB_ID, "草工艺");
        }
    }
}
