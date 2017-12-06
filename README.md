# ForgePermManager
Forge Permission Manager for Minecraft

- 权限导出yml或json格式：服务器端导出到json/yml文件夹，客户端导出客户端对应文件夹。
- 支持双端权限的导出和导入
- 游戏内权限GUI编辑器
```
G
l d
s
l p
l p
l p
E
P
l n
    l n
        l n
        0
    0
    l n
    0
    l n
    0
0
E

WB  'G'
WB  display.length
WBS display.bytes
WB  groups.size
for i = 1,size
    WB  group[i].length
    WBS group[i].bytes
end
WB  'E'
WB  'P'
NODE.W
WB  'E'
```