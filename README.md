# ForgePermManager
Forge Permission Manager for Minecraft

- 权限导出yml或json格式：服务器端导出到json/yml文件夹，客户端导出客户端对应文件夹。
- 支持双端权限的导出和导入
- 游戏内权限GUI编辑器

```
/fpm perm add/remove <player> <perm1>,<perm2>...
/fpm group add/remove <player> <group1>,<group2>...

/fpm group create <name>
/fpm group delete <name>
/fpm group addp <name> <perm>
/fpm group delp <name> <perm>
/fpm group setparent <name> <parent>
/fpm group delparent <name>
/fpm group addsub <name> <sub>
/fpm group delsub <name> <sub>
/fpm group setvalue <name> <key> <value>
/fpm group delvalue <name> <key>
/fpm group save <name>
```