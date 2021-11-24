# YamlEdit

commandline YAML editor

## Download

Download from here ↓

## Usage

As a premise, I will call a key-value pair a **dictionary**.

1. Select a file you want to edit like this:

   `/edit select [file directory]`

   Where `[file directory]` is a relative path from your server's **plugin directory**.

2. Edit YAML config using these commands:

    - Edit dictionary

      `/edit m [key] [value]`

      Where `[key]` is a YAML key in selected YAML file that you want to change the value, `[value]` is a value that you want to change in chosen `[key]`.

    - Append value to the selected key

      `/edit a [key] [value]`

      Where `[key]` is a YAML key in selected YAML file that you want to append the value,  `[value]` is a value that you want to append in chosen `[key]`.

    - Append new dictionary to the selected key

      `/edit a [key1] [key2] [value]`

      Where `[key1]` is a YAML key in selected YAML file that you want to append the new dictionary,  `[key2]` and`[value]` is a key-value pair to append in chosen `[key1]`

## Examples

Here are some examples in using this plugin 😄

I'll use this YAML file as a example ↓

```yaml
gamemode: survival
item-list: [12, 453, 212]
zombie:
    health-limit: 20
    silent-mode: true
```

### BASIC: Edit the value of the dictionary

When you want to edit the key `gamemode`'s value to `hardcore`, type:

```
/edit m gamemode hardcore
```

After selecting the file.

<details>

<summary>This is the yaml output after running the command</summary>

```yaml
gamemode: hardcore
item-list: [12, 453, 212]
zombie:
    health-limit: 20
    silent-mode: true
```
</details>

### BASIC: Append value to the selected key

When you want to append a value `4` to the key `item-list`, type:

```
/edit a item-list 4
```

After selecting the file.

<details>

<summary>This is the yaml output after running the command</summary>

```yaml
gamemode: survival
item-list: [12, 453, 212]
zombie:
    health-limit: 20
    silent-mode: true
```
</details>

### BASIC: Append new dictionary to the selected key

When you want to append new dictionary `friendly: true` to the key `zombie`, type:

```
/edit a zombie friendly true
```

<details>

<summary>This is the yaml output after running the command</summary>

```yaml
gamemode: survival
item-list: [12, 453, 212]
zombie:
    health-limit: 20
    silent-mode: true
    friendly: true
```
</details>

### Edit value of a nested key

For example, when you had a yaml file like this:

and you want to change the `zombie`'s `health-limit`to`30`, after selecting the file, type the command like this to do it.

```
/edit m zombie.health-limit 30
```

So, when passing nested keys as arguments, separate the paths with dots (`.`) like this.

<details>

<summary>This is the yaml output after running the command</summary>

```yaml
gamemode: survival
item-list: [12, 453, 212]
zombie:
    health-limit: 30
    silent-mode: true
```
</details>

### How to express a string value

To express a string (e.g. `"I am a god"`, `"parsing is difficlt"`) value, just put `"` around the value.

If you wanted to append `"apple"` to `item-list`, you can type in like this:

```
/edit a item-list "apple"
```

<details>

<summary>This is the yaml output after running the command</summary>

```yaml
gamemode: survival
item-list: [12, 453, 212, "apple"]
zombie:
    health-limit: 30
    silent-mode: true
```
</details>

---

I might add more examples if necessary.

## How to report BUGs 🐛🐜🐝

- Post in GitHub Issues if you are capable of it ← Everyone can google it
- DM me in discord (ID:`temps1101#`) ← Likely to reply speedy