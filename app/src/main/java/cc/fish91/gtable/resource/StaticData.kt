package cc.fish91.gtable.resource

import cc.fish91.gtable.*
import cc.fish91.gtable.engine.FightEffects
import cc.fish91.gtable.engine.IFightEffect

object StaticData {

    /****Static Data**************/

    private val mMonsterMap = mutableMapOf(
            Pair(1, BaseMonster(2, 1, 1, "花蘑菇", 2, 1, R.drawable.t_icon_monster_mashroom, Pair(0x1, 10), FightEffects.CutP10HP::class)),
            Pair(2, BaseMonster(4, 1, 2, "钢猪猪", 5, 3, R.drawable.t_icon_monster_steelpig, Pair(0x1001, 20), FightEffects.DcsPDef::class)),
            Pair(3, BaseMonster(1, 3, 1, "星星怪", 5, 6, R.drawable.t_icon_monster_star, Pair(0x2001, 25), FightEffects.DscPAtk::class)),
            Pair(4, BaseMonster(2, 3, 3, "斧木妖", 5, 6, R.drawable.t_icon_monster_axewood, Pair(0x0002, 25), FightEffects.ClearPArmor::class)),
            Pair(5, BaseMonster(3, 2, 2, "太阳怪", 5, 6, R.drawable.t_icon_monster_sun, Pair(0x1002, 25), FightEffects.DscFA1Def::class))
    )

    private val mAreaMonsterMap = mutableMapOf(
            Pair(1, listOf(1, 2, 3)),
            Pair(2, listOf(2, 3, 5)),
            Pair(3, listOf(3, 4, 5))
    )

    private val mEquipInfoMap = mutableMapOf(
            Pair(0x0001, EquipInfo(0x0001, "布衣", EquipPosition.ARMOR, 2, R.drawable.t_icon_a_001, null)),
            Pair(0x1001, EquipInfo(0x1001, "短剑", EquipPosition.WEAPON, 2, R.drawable.t_icon_w_001, null)),
            Pair(0x2001, EquipInfo(0x2001, "蓝宝石戒指", EquipPosition.RING, 2, R.drawable.t_icon_r_001, null)),
            Pair(0x0002, EquipInfo(0x0002, "木甲", EquipPosition.ARMOR, 4, R.drawable.t_icon_a_002, null)),
            Pair(0x1002, EquipInfo(0x1002, "长剑", EquipPosition.WEAPON, 4, R.drawable.t_icon_w_002, null)),
            Pair(0x2002, EquipInfo(0x2002, "蓝魂戒指", EquipPosition.RING, 4, R.drawable.t_icon_r_002, null))
    )


    /****Monster****************/
    //  will add CACHE  later!!
    fun getBaseMonster(id: Int) = mMonsterMap[id]!!

    fun getMonsterPool(areaId: Int) = if (areaId <= mAreaMonsterMap.size - 1) mAreaMonsterMap[areaId]!! else mAreaMonsterMap[mAreaMonsterMap.size - 1]!!


    /****Gift ********************/
    fun getGiftInfo(gift: Gifts) = when (gift) {
        Gifts.ATK_UP -> Pair(R.drawable.t_icon_atk, gift.desc)
        Gifts.DEF_UP -> Pair(R.drawable.t_icon_def, gift.desc)
        Gifts.HP_ARMOR -> Pair(R.drawable.t_icon_buff_armor, gift.desc)
    }

    /**** Buff**********************/
    fun getBuffInfo(buffA: BuffAbility) = when (buffA) {
        BuffAbility.ATK -> Pair(R.drawable.t_icon_buff_atk, buffA.desc)
        BuffAbility.DEF -> Pair(R.drawable.t_icon_buff_def, buffA.desc)
//        BuffAbility.ARMOR -> Pair(R.drawable.t_icon_buff_armor, buffA.desc)
    }


    /****Person Property********/

    fun getLvGrow(roleType: RoleType = RoleType.BEGINNER) = when (roleType) {
        RoleType.BEGINNER -> PersonData(20, 2, 2, 0, 0, 0, 0)
        else -> PersonData(20, 2, 2, 0, 0, 0, 0)
    }

    fun getUPFee(currentLevel: Int) = 500 * currentLevel + 200

    fun getUppedAtk(originAtk: Int, atkLv: Int) = (originAtk * (1 + 0.1 * atkLv) + atkLv).toInt()
    fun getUppedDef(originDef: Int, defLv: Int) = (originDef * (1 + 0.1 * defLv) + defLv).toInt()
    fun getUppedHP(originHP: Int, hpLv: Int) = (originHP * (1 + 0.1 * hpLv) + hpLv * 10).toInt()

    fun getLimitExp(level: Int) = level * level * 20

    fun statusUpCalc(person: PersonData, personBought: PersonBought) {
        person.let {
            it.HP = getUppedHP(person.HP, personBought.hpLv)
            it.atk = getUppedHP(person.atk, personBought.atkLv)
            it.def = getUppedHP(person.def, personBought.defLv)
        }
    }

    /****Equip Area**********************/

    fun getBaseEquipInfo(id: Int) = mEquipInfoMap[id]!!

    fun getAllEquips() = mEquipInfoMap.keys

}